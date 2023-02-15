package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.Customer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class CustomerSerde {
    public static Serde<Customer> customerSerde = Serdes.serdeFrom(new CustomerSerializer(),
        new CustomerDeSerializer());
}
