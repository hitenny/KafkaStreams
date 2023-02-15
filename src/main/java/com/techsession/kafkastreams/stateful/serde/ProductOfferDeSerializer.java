package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.ProductOffer;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class ProductOfferDeSerializer implements Deserializer<ProductOffer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public ProductOffer deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, ProductOffer.class);
    }
}
