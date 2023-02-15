package com.techsession.kafkastreams.stateful.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class ProductOffer {
    private String productId;
    private int discount;
}
