package com.ilozanof.learning.kafka.serialization.avro;

import com.ilozanof.learning.kafka.serializationAvro.StockData;
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
    @Value("${schema.registry.url}") String schemaRegistry;

    public void run() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", keySerializer);
        properties.put("value.serializer", valueSerializer);
        properties.put("schema.registry.url", schemaRegistry);

        KafkaProducer kafkaProducer = new KafkaProducer(properties);
        try {
            for (int i = 0; i < 100; i++) {
                // We create an instance of the avro-generated java class and fill it with dummy values:
                System.out.println("Producing message " + i);
                StockData record = new StockData();
                record.setSymbol("Symbol " + i);
                kafkaProducer.send(new ProducerRecord<String, StockData>(topic, record));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }

}
