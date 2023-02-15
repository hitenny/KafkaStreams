package com.techsession.kafkastreams.stateful.valueJoiner;

import com.techsession.kafkastreams.stateful.model.OrderItem;
import com.techsession.kafkastreams.stateful.model.ProductOffer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ValueJoiner;

@Slf4j
public class ProductDiscountEnricher implements ValueJoiner<OrderItem, ProductOffer, OrderItem> {
    @Override
    public OrderItem apply(OrderItem orderItem, ProductOffer productOffer) {
        log.info("Enriching product discount {} for order {}", orderItem.getProduct().getId(),
            orderItem.getOrderId());

        OrderItem.OrderItemBuilder updatedOrderItem =  orderItem.toBuilder();
        if(productOffer != null) {

            log.info("Applying discount. After price: {}",
                orderItem.getAmount() - ((double)productOffer.getDiscount()/100 * orderItem.getAmount()));
            updatedOrderItem.amountAfterDiscount(orderItem.getAmount() - ((double)productOffer.getDiscount()/100 * orderItem.getAmount()));
        }
        else {
            updatedOrderItem.amountAfterDiscount(orderItem.getAmount());
        }

        return updatedOrderItem.build();
    }
}
