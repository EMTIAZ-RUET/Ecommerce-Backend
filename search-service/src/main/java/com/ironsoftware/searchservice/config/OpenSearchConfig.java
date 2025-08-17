package com.ironsoftware.searchservice.config;

import org.apache.http.HttpHost;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig {

    @Value("${opensearch.host}")
    private String host;

    @Value("${opensearch.port}")
    private int port;

    @Value("${opensearch.protocol}")
    private String protocol;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient openSearchClient() {
        return new RestHighLevelClient(
            RestClient.builder(
                new HttpHost(host, port, protocol)
            )
        );
    }
}
