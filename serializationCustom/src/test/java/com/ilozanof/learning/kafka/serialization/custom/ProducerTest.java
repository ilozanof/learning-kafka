package com.ilozanof.learning.kafka.serialization.custom;

import com.ilozanof.learning.kafka.serialization.custom.Producer;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void testProducer() {
        producer.run();
    }
}
