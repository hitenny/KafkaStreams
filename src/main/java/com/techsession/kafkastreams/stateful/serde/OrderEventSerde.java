package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.OrderEvent;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class OrderEventSerde {
    public static Serde<OrderEvent> orderEventSerde = Serdes.serdeFrom(new OrderEventSerializer(),
        new OrderEventDeSerializer());
}
