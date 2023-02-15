package com.techsession.kafkastreams.stateful.valueJoiner;

import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import com.techsession.kafkastreams.stateful.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ValueJoiner;

@Slf4j
public class ProductEnricher implements ValueJoiner<OrderEvent, Product, OrderItem> {
    @Override
    public OrderItem apply(OrderEvent orderEvent, Product product) {
        log.info("Enriching product {} for order {}", orderEvent.getProductId(), orderEvent.getOrderId());
        return OrderItem.builder().orderId(orderEvent.getOrderId())
                   .product(product)
                   .customerId(orderEvent.getCustomerId())
                   .rate(product.getRate())
                   .quantity(orderEvent.getQuantity())
                   .amount(orderEvent.getQuantity() * product.getRate())
                   .build();
    }
}
