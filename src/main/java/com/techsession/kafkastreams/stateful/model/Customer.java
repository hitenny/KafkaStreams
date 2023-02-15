package com.techsession.kafkastreams.stateful.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class Customer {
    private String id;
    private String name;
    private MembershipType membershipType;
    private String address;
}
