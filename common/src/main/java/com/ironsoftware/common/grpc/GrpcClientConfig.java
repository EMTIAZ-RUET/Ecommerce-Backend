package com.ironsoftware.common.grpc;

import io.grpc.ClientInterceptor;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @GrpcGlobalClientInterceptor
    public ClientInterceptor correlationIdInterceptor() {
        return MetadataUtils.newAttachHeadersInterceptor(metadata());
    }

    private Metadata metadata() {
        Metadata metadata = new Metadata();
        Metadata.Key<String> key = Metadata.Key.of("correlation-id", 
            Metadata.ASCII_STRING_MARSHALLER);
        metadata.put(key, org.slf4j.MDC.get("correlationId"));
        return metadata;
    }
}
