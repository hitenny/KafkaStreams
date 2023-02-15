package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class OrderItemSerde {
    public static Serde<OrderItem> orderItemSerde = Serdes.serdeFrom(new OrderItemSerializer(),
        new OrderItemDeSerializer());
}
