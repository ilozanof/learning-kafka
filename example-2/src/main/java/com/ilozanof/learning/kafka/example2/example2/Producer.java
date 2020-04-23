package com.ilozanof.learning.kafka.example2.example2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-22 18:06
 */
@Component
public class Producer {

    @Value("${bootstrap.servers}") String bootstrapServers;
    @Value("${key.serializer}") String keySerializer;
    @Value("${value.serializer}") String valueSerializer;
    @Value("${topic}") String topic;

    public void run() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", keySerializer);
        properties.put("value.serializer", valueSerializer);

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Producing message " + i);
                kafkaProducer.send(new ProducerRecord(topic, Integer.toString(i), "test message - " + i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }

}
