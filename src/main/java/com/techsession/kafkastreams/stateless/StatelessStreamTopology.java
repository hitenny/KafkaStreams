package com.techsession.kafkastreams.stateless;

import com.techsession.kafkastreams.TopologyProvider;
import java.util.Arrays;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Component;

@Component("stateless")
public class StatelessStreamTopology implements TopologyProvider {
    @Override
    public Topology topology() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> sentenceStream = streamsBuilder.stream("sentences");
        sentenceStream.flatMap((k, v) ->
                                   Arrays.stream(v.split(" "))
                                       .map(word -> new KeyValue<String, String>(String.valueOf(word.charAt(0)), word))
                                       .toList())
            .mapValues(val -> val.toUpperCase())
            .filter((k, v) -> v.length() > 2)
            .to("capsWords");

        KStream<String, String> capsWordsStream = streamsBuilder.stream("capsWords");
        capsWordsStream.split()
            .branch((k,v) -> v.startsWith("A") || v.startsWith("E") || v.startsWith("I") || v.startsWith("O") || v.startsWith("U"),
                Branched.withConsumer(ks -> ks.to("vowelWords")))
            .defaultBranch(Branched.withConsumer(ks -> ks.to("nonVowelWords")));

        return streamsBuilder.build();
    }
}
