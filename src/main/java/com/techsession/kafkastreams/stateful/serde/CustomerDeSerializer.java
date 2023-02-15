package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomerDeSerializer implements Deserializer<Customer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public Customer deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, Customer.class);
    }
}
