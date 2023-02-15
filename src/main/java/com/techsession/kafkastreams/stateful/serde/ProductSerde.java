package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.Product;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class ProductSerde {
    public static Serde<Product> productSerde = Serdes.serdeFrom(new ProductSerializer(),
        new ProductDeSerializer());
}
