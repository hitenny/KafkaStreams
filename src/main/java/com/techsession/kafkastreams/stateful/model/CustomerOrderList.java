package com.techsession.kafkastreams.stateful.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder(toBuilder = true)
@Getter
@Jacksonized
public class CustomerOrderList {
    private Customer customer;
    private List<OrderItem> itemList;
    private double billAmount;
    private double savings;

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n************\n");
        builder.append("Customer Name: " + customer.getName());
        builder.append("\nCustomer Address: " + customer.getAddress());
        builder.append("\nMembership: " + customer.getMembershipType());
        builder.append("\nOrder ID: " + itemList.get(0).getOrderId());
        builder.append("\nOrder Details\n");
        builder.append(String.format("%-5s %-15s %-5s %-3s %-5s %-5s", "Sl.No",
            "Item", "Rate", "Qty", "Amount", "Amount after discount"));
        builder.append("\n************\n");
        for(int i=0; i< itemList.size(); i++) {
            builder.append(i+1 + ". ");
            builder.append(itemList.get(i).toString());
            builder.append("\n");
        }
        builder.append("--------------\n");
        builder.append("Bill amount:" + billAmount);
        builder.append("\nSavings:" + savings + "\n");

        return builder.toString();
    }
}
