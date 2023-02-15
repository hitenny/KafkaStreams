package com.techsession.kafkastreams.stateful.valueJoiner;

import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.CustomerOrderList;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.ValueJoiner;

@Slf4j
public class CustomerEnricher implements ValueJoiner<CustomerOrderList, Customer, CustomerOrderList> {
    @Override
    public CustomerOrderList apply(CustomerOrderList customerOrderList, Customer customer) {
        log.info("Enriching customer {} for order {}", customer.getId(),
            customerOrderList.getItemList().get(0).getOrderId());

        return customerOrderList.toBuilder()
                   .customer(customer)
                   .billAmount(customerOrderList.getItemList().stream().mapToDouble(OrderItem::getAmount).sum())
                   .savings(customerOrderList.getItemList().stream().mapToDouble(item -> item.getAmount()-item.getAmountAfterDiscount()).sum())
                   .build();
    }
}
