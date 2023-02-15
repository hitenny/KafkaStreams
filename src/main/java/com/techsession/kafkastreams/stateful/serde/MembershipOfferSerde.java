package com.techsession.kafkastreams.stateful.serde;

import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class MembershipOfferSerde {
    public static Serde<MembershipOffer> membershipOfferSerde = Serdes.serdeFrom(new MembershipOfferSerializer(),
        new MembershipOfferDeSerializer());
}
