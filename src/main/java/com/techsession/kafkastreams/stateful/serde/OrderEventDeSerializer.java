package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class OrderEventDeSerializer implements Deserializer<OrderEvent> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public OrderEvent deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, OrderEvent.class);
    }
}
