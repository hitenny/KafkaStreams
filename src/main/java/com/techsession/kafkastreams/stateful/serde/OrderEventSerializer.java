package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class OrderEventSerializer implements Serializer<OrderEvent> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, OrderEvent orderEvent) {
        return objectMapper.writeValueAsString(orderEvent).getBytes();
    }
}
