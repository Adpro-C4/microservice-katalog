package com.adpro.katalog.eventdriven;

import com.adpro.katalog.api.API;
import com.adpro.katalog.model.dto.ProductDTO;
import com.adpro.katalog.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQListener {
    private final ProductService productService;

    public RabbitMQListener(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "stock-product-queue")
    public void receiveSuccessfullTransaction(String message) {
        RestTemplate rest = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = rest.
                getForEntity(API.GET_ORDER_DETAILS.getUrl() + message, JsonNode.class);
        JsonNode responseBody = responseEntity.getBody();

        if (responseBody != null) { // Check if responseBody is not null
            JsonNode orderNode = responseBody.get("data").get("order");
            JsonNode cartItems = orderNode.get("cartItems");
            List<ProductDTO> productDTOs = jsonToProductDTOList(cartItems);
            productService.updateProductsStock(productDTOs);
        } else {
            // Handle the case where responseBody is null, such as logging an error or throwing an exception.
        }
    }


    public List<ProductDTO> jsonToProductDTOList(JsonNode cartItemsNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductDTO> productDTOList = new ArrayList<>();
        if (cartItemsNode != null && cartItemsNode.isArray()) {
            for (JsonNode itemNode : cartItemsNode) {
                ProductDTO productDTO;
                try {
                    productDTO = objectMapper.treeToValue(itemNode, ProductDTO.class);
                    productDTOList.add(productDTO);
                } catch (JsonProcessingException | IllegalArgumentException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        return productDTOList;
    }
}
