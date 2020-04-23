package com.ilozanof.learning.kafka.basic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-22 18:25
 */
@SpringBootTest
public class ProducerTest {

    @Autowired
    Producer producer;

    @Test
    public void testProducer() {
        producer.run();
    }
}
