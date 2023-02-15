package com.techsession.kafkastreams;

import com.techsession.kafkastreams.stateful.StatefulStreamTopology;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class StreamTopology {
    private final StatefulStreamTopology topologyProvider;
    private final KafkaStreamsConfig kafkaStreamsConfig;

    @PostConstruct
    public void init() {
        KafkaStreams streams = new KafkaStreams(topologyProvider.topology(),
            kafkaStreamsConfig.kafkaStreamsConfiguration().asProperties());
        streams.start();
        log.info("Topology: {}", topologyProvider.topology().describe());
    }
}
