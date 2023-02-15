package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.CustomerOrderList;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomerOrderListDeSerializer implements Deserializer<CustomerOrderList> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public CustomerOrderList deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, CustomerOrderList.class);
    }
}
