package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderItemDeSerializer implements Deserializer<OrderItem> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public OrderItem deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, OrderItem.class);
    }
}
