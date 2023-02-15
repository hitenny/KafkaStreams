package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

public class MembershipOfferDeSerializer implements Deserializer<MembershipOffer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public MembershipOffer deserialize(String s, byte[] bytes) {
        return objectMapper.readValue(bytes, MembershipOffer.class);
    }
}
