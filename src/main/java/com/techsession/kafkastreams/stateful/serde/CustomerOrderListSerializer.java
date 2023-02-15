package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.CustomerOrderList;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class CustomerOrderListSerializer implements Serializer<CustomerOrderList> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, CustomerOrderList customerOrderList) {
        return objectMapper.writeValueAsString(customerOrderList).getBytes();
    }
}
