package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class CustomerSerializer implements Serializer<Customer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, Customer customer) {
        return objectMapper.writeValueAsString(customer).getBytes();
    }
}
