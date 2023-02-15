package com.techsession.kafkastreams.stateful.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import java.util.Map;
import lombok.SneakyThrows;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

public class MembershipOfferSerializer implements Serializer<MembershipOffer> {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    @SneakyThrows
    @Override
    public byte[] serialize(String s, MembershipOffer membershipOffer) {
        return objectMapper.writeValueAsString(membershipOffer).getBytes();
    }
}
