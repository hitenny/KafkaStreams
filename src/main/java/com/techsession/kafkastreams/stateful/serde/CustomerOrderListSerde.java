package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.CustomerOrderList;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class CustomerOrderListSerde {
    public static Serde<CustomerOrderList> customerOrderListSerde = Serdes.serdeFrom(new CustomerOrderListSerializer(),
        new CustomerOrderListDeSerializer());
}
