package com.ironsoftware.authservice.service;

import com.ironsoftware.authservice.dto.LoginRequest;
import com.ironsoftware.authservice.dto.RegisterRequest;
import com.ironsoftware.authservice.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    public TokenResponse login(LoginRequest request) {
        try {
            Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(request.getUsername())
                .password(request.getPassword())
                .build();

            AccessTokenResponse response = keycloak.tokenManager().getAccessToken();

            return TokenResponse.builder()
                .accessToken(response.getToken())
                .refreshToken(response.getRefreshToken())
                .expiresIn(response.getExpiresIn())
                .refreshExpiresIn(response.getRefreshExpiresIn())
                .tokenType(response.getTokenType())
                .build();
        } catch (Exception e) {
            throw new RuntimeException("Authentication failed", e);
        }
    }

    public void register(RegisterRequest request) {
        try {
            Keycloak adminClient = getAdminClient();
            RealmResource realmResource = adminClient.realm(realm);
            UsersResource usersResource = realmResource.users();

            UserRepresentation user = new UserRepresentation();
            user.setEnabled(true);
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());

            Response response = usersResource.create(user);
            if (response.getStatus() != 201) {
                throw new RuntimeException("Failed to create user");
            }

            String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(request.getPassword());
            credential.setTemporary(false);

            usersResource.get(userId).resetPassword(credential);

            // Assign default role
            realmResource.users().get(userId).roles()
                .realmLevel()
                .add(Collections.singletonList(
                    realmResource.roles().get("user").toRepresentation()
                ));
        } catch (Exception e) {
            throw new RuntimeException("Registration failed", e);
        }
    }

    private Keycloak getAdminClient() {
        return KeycloakBuilder.builder()
            .serverUrl(authServerUrl)
            .realm("master")
            .clientId("admin-cli")
            .username("admin")
            .password("admin")
            .build();
    }
}
