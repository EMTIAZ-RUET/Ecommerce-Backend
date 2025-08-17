package com.ironsoftware.searchservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironsoftware.common.service.BaseService;
import com.ironsoftware.searchservice.model.ProductDocument;
import lombok.RequiredArgsConstructor;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.search.SearchRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchService extends BaseService {

    private final RestHighLevelClient openSearchClient;
    private final ObjectMapper objectMapper;
    private static final String PRODUCT_INDEX = "products";

    public void indexProduct(ProductDocument product) {
        executeWithResilience(() -> {
            try {
                IndexRequest request = new IndexRequest(PRODUCT_INDEX)
                    .id(product.getId())
                    .source(objectMapper.writeValueAsString(product), XContentType.JSON);
                
                openSearchClient.index(request, RequestOptions.DEFAULT);
                return null;
            } catch (Exception e) {
                throw new RuntimeException("Failed to index product", e);
            }
        });
    }

    public List<ProductDocument> searchProducts(String query, int page, int size) {
        return executeWithResilience(() -> {
            SearchRequest searchRequest = new SearchRequest(PRODUCT_INDEX);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.multiMatchQuery(query)
                    .field("name", 2.0f)
                    .field("description")
                    .field("category")
                    .field("tags"))
                .from(page * size)
                .size(size);

            searchRequest.source(sourceBuilder);
            
            try {
                return Arrays.stream(openSearchClient
                    .search(searchRequest, RequestOptions.DEFAULT)
                    .getHits()
                    .getHits())
                    .map(hit -> {
                        try {
                            return objectMapper.readValue(hit.getSourceAsString(), ProductDocument.class);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to parse search result", e);
                        }
                    })
                    .collect(Collectors.toList());
            } catch (Exception e) {
                throw new RuntimeException("Failed to search products", e);
            }
        });
    }
}
