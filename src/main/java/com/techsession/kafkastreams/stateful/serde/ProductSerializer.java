package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Product;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class ProductSerializer implements Serializer<Product> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, Product product) {
        return objectMapper.writeValueAsString(product).getBytes();
    }
}
