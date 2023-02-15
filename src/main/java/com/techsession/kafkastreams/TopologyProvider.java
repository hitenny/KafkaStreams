package com.techsession.kafkastreams;

import org.apache.kafka.streams.Topology;

public interface TopologyProvider {
    Topology topology();
}
