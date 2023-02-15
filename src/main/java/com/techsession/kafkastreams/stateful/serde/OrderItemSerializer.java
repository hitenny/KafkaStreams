package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class OrderItemSerializer implements Serializer<OrderItem> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, OrderItem orderItem) {
        return objectMapper.writeValueAsString(orderItem).getBytes();
    }
}
