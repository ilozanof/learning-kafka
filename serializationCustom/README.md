

# Custom Serialization

This example builds on top of the *basic* example:

 * It uses the Kafka libraries for Java (but not the spring Template)
 * It uses Spring for dependency injection and @Values population
 
 The code is quite straightforward, the Producer creates and pushes 100 records to a Topic 
 in the Kafka server, then it finishes.
 
 The Consumer runs in a infinite loop reading messages from the topic.

Changes in this Project:
 
 * This time, the Records pushed into the Topic are not just Sting, but a POJO. This POJO needs 
 to be serialized and Deserialized, and so we provide both classes which have been developed from 
 scratch using a very basic serialization logic, relying on the *ObjectMapper* class in the *JDK*
 
 * The definition in the infrastructure for *docker*^is slightly different (details further down)
 
 To test it, we run the Consumer and keep it running. Then we run the Producer (several 
 times if needed). Every time we run the producer, we'll see in the logs how the producer pushes
 the messages, and how the consumer consumes them.

## Infrastructure

The *docker-compose.yml* defines a basic Kafka installation with only one server/broker:

**This time, the images used for the component ins the Kafka infrastructure are NOt the sames as in the basic
 example. Instead of using the ones from bitmap, we are using one the official ones from _confluentic_**

```
version: '2'

networks:
  kafka-net:
    driver: bridge

services:
  zookeeper-server:
    image: 'confluentinc/cp-zookeeper:5.1.0'
    hostname: zookeeper-server
    networks:
      - kafka-net
    ports:
      - '2181:2181'
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
  kafka-server1:
    image: 'confluentinc/cp-kafka:5.1.0'
    networks:
      - kafka-net
    ports:
      - '9092:9092'
      - '29092:29092'
    environment:
      - KAFKA_ZOOKEEPER_CONNECT=zookeeper-server:2181
      - KAFKA_CFG_LISTENERS=INTERNAL://:29092,EXTERNAL://:9092
      - KAFKA_ADVERTISED_LISTENERS=INTERNAL://kafka-server1:29092,EXTERNAL://localhost:9092
      - KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
      - KAFKA_INTER_BROKER_LISTENER_NAME=INTERNAL
      - KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1
    depends_on:
      - zookeeper-server
    restart: on-failure
``` 
 ## Pre-requisites
 
 * The Kafka server must be running, and the port its listening to must be compatible with the
 configuration stored in the `application.properties` in this example.
 
