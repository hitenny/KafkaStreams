package com.techsession.kafkastreams;

import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.Product;
import com.techsession.kafkastreams.stateful.model.ProductOffer;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class KafkaDriver {
    private final KafkaTemplate<String, Customer> customerKafkaTemplate;
    private final KafkaTemplate<String, Product> productKafkaTemplate;
    private final KafkaTemplate<String, OrderEvent> orderEventKafkaTemplate;
    private final KafkaTemplate<String, MembershipOffer> membershipOfferKafkaTemplate;
    private final KafkaTemplate<String, ProductOffer> productOfferKafkaTemplate;

    @PostMapping("customers")
    public void loadCustomers(@RequestBody List<Customer> customers) {
        customers.forEach(customer -> customerKafkaTemplate.send("customer", customer.getId(), customer));
    }

    @PostMapping("products")
    public void loadProducts(@RequestBody List<Product> products) {
        products.forEach(product -> productKafkaTemplate.send("productCatalog", product.getId(), product));
    }

    @PostMapping("membershipOffers")
    public void loadMemOffers(@RequestBody List<MembershipOffer> membershipOffers) {
        membershipOffers.forEach( membershipOffer -> membershipOfferKafkaTemplate.send("membershipOffer",
            membershipOffer));
    }

    @PostMapping("productOffers")
    public void loadProductOffers(@RequestBody List<ProductOffer> productOffers) {
        productOffers.forEach(productOffer -> productOfferKafkaTemplate.send("productOffer",
            productOffer.getProductId(),
            productOffer));
    }

    @PostMapping("orders")
    public void loadOrders(@RequestBody List<OrderEvent> orderEvents) {
        orderEvents.forEach(orderEvent -> orderEventKafkaTemplate.send("order", orderEvent.getProductId(), orderEvent));
    }
}
