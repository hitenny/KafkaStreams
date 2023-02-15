package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.Product;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class ProductDeSerializer implements Deserializer<Product> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public Product deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, Product.class);
    }
}
