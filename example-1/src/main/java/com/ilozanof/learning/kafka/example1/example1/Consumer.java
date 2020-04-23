package com.ilozanof.learning.kafka.example1.example1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-22 18:27
 */
@Component
public class Consumer {

    @Value("${bootstrap.servers}") String bootstrapServers;
    @Value("${key.deserializer}") String keySerializer;
    @Value("${value.deserializer}") String valueSerializer;
    @Value("${group.id}") String groupId;
    @Value("${topic}") String topic;

    public void run() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.deserializer", keySerializer);
        properties.put("value.deserializer", valueSerializer);
        properties.put("group.id", groupId);

        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        List topics = Arrays.asList(topic);
        kafkaConsumer.subscribe(topics);

        try {
            while(true) {
                ConsumerRecords records = kafkaConsumer.poll(Duration.ofMillis(10));
                Iterator it = records.records(topic).iterator();
                while (it.hasNext()) {
                    ConsumerRecord record = (ConsumerRecord) it.next();
                    System.out.println("Consuming message :: topic : "
                            + record.topic() + ", Partition: "
                            + record.partition() + ", Value: " + record.value());
                }
            } // while...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }

    }
}
