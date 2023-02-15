package com.techsession.kafkastreams.stateful;

import com.techsession.kafkastreams.TopologyProvider;
import com.techsession.kafkastreams.stateful.model.Customer;
import com.techsession.kafkastreams.stateful.model.CustomerOrderList;
import com.techsession.kafkastreams.stateful.model.MembershipOffer;
import com.techsession.kafkastreams.stateful.model.OrderEvent;
import com.techsession.kafkastreams.stateful.model.OrderItem;
import com.techsession.kafkastreams.stateful.model.Product;
import com.techsession.kafkastreams.stateful.model.ProductOffer;
import com.techsession.kafkastreams.stateful.serde.CustomerOrderListSerde;
import com.techsession.kafkastreams.stateful.serde.CustomerSerde;
import com.techsession.kafkastreams.stateful.serde.MembershipOfferSerde;
import com.techsession.kafkastreams.stateful.serde.OrderEventSerde;
import com.techsession.kafkastreams.stateful.serde.OrderItemSerde;
import com.techsession.kafkastreams.stateful.serde.ProductOfferSerde;
import com.techsession.kafkastreams.stateful.serde.ProductSerde;
import com.techsession.kafkastreams.stateful.valueJoiner.CustomerEnricher;
import com.techsession.kafkastreams.stateful.valueJoiner.ProductDiscountEnricher;
import com.techsession.kafkastreams.stateful.valueJoiner.ProductEnricher;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;

@Slf4j
@Component("stateful")
public class StatefulStreamTopology implements TopologyProvider {
    private static final String CUSTOMER_TOPIC = "customer";
    private static final String PRODUCT_TOPIC = "productCatalog";
    private static final String MEMBERSHIP_OFFER_TOPIC = "membershipOffer";
    private static final String PRODUCT_OFFER_TOPIC = "productOffer";
    private static final String ORDER_TOPIC = "order";

    @Override
    public Topology topology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        GlobalKTable<String, MembershipOffer> membershipOfferGlobalKTable =
            streamsBuilder.globalTable(MEMBERSHIP_OFFER_TOPIC,
                Consumed.with(Serdes.String(), MembershipOfferSerde.membershipOfferSerde));

        KTable<String, Customer> customerKTable = streamsBuilder.table(CUSTOMER_TOPIC, Consumed.with(Serdes.String(),
            CustomerSerde.customerSerde));

        KTable<String, ProductOffer> productOfferKTable = streamsBuilder.table(PRODUCT_OFFER_TOPIC, Consumed.with(Serdes.String(),
            ProductOfferSerde.productOfferSerde));

        KTable<String, Product> productKTable = streamsBuilder.table(PRODUCT_TOPIC, Consumed.with(Serdes.String(),
            ProductSerde.productSerde));

        KStream<String, OrderEvent> orderStream = streamsBuilder.stream(ORDER_TOPIC, Consumed.with(Serdes.String(),
            OrderEventSerde.orderEventSerde));

        KStream<String, CustomerOrderList> customerOrder = orderStream.join(productKTable, new ProductEnricher())
            .leftJoin(productOfferKTable, new ProductDiscountEnricher())
            .map((productId, orderItem) -> new KeyValue<>(orderItem.getCustomerId(), orderItem))
            .groupByKey(Grouped.with(Serdes.String(), OrderItemSerde.orderItemSerde))
            .aggregate(() -> CustomerOrderList.builder().itemList(new ArrayList<>()).build(),
                (s, orderItem, customerOrderList) -> {
                List<OrderItem> orderItems = customerOrderList.getItemList();
                orderItems.add(orderItem);
                return customerOrderList.toBuilder().itemList(orderItems).build();
            }, Materialized.with(Serdes.String(), CustomerOrderListSerde.customerOrderListSerde))
            .join(customerKTable, new CustomerEnricher())
            .toStream();

        customerOrder.foreach((s, customerOrderList) -> log.info("Customer Order: {}", customerOrderList.toString()));
        customerOrder.to("billing", Produced.with(Serdes.String(), CustomerOrderListSerde.customerOrderListSerde));
        return streamsBuilder.build();
    }
}
