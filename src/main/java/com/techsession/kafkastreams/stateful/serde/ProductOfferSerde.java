package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.ProductOffer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class ProductOfferSerde {
    public static Serde<ProductOffer> productOfferSerde = Serdes.serdeFrom(new ProductOfferSerializer(),
        new ProductOfferDeSerializer());
}
