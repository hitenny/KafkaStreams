package com.techsession.kafkastreams.stateful.model;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder(toBuilder=true)
@Getter
@Jacksonized
public class OrderItem {
    private String orderId;
    private String customerId;
    private Product product;
    private int quantity;
    private double rate;
    private double amount;
    private double amountAfterDiscount;

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%-15s %.2f %-3d %.2f %.2f", product.getName(),
            product.getRate(), quantity, amount, amountAfterDiscount));
        return builder.toString();
    }
}
