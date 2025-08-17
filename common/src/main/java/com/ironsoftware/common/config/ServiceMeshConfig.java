package com.ironsoftware.common.config;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceMeshConfig {

    @Bean
    public ApiClient kubernetesApiClient() throws Exception {
        return Config.defaultClient();
    }

    @Bean
    public CoreV1Api kubernetesApi(ApiClient client) {
        return new CoreV1Api(client);
    }

    // Configure Istio/Linkerd integration
    // This would typically be done through Kubernetes manifests
    // and service mesh configuration files rather than in code
}
