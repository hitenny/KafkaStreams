package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.ProductOffer;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class ProductOfferSerializer implements Serializer<ProductOffer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, ProductOffer productOffer) {
        return objectMapper.writeValueAsString(productOffer).getBytes();
    }
}
