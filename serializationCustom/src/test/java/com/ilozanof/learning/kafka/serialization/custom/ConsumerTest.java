package com.ilozanof.learning.kafka.serialization.custom;




import com.ilozanof.learning.kafka.serialization.custom.Consumer;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author i.fernandez@nchain.com
 * @date 2020-04-22 18:43
 */
@SpringBootTest
public class ConsumerTest {

    @Autowired
    Consumer consumer;

    @Ignore
    @Test
    public void testConsumer() {
        consumer.run();
    }
}
