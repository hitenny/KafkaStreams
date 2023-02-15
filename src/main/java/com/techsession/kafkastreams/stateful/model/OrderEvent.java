package com.techsession.kafkastreams.stateful.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class OrderEvent {
    private String orderId;
    private String customerId;
    private String productId;
    private int quantity;
}
