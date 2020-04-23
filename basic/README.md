# Example-1

## A simple consumer and Producer

 * This simple project uses the Kafka libraries for Java (but not the spring Template)
 * It uses Spring for dependency injection and @Values population
 
 The code is quite straightforward, the Producer creates and pushes 100 records to a Topic 
 in the Kafka server, then it finishes.
 
 The Consumer runs in a infinite loop reading messages from the topic.
 
 So to test it, we run the Consumer and keep it running. Then we run the Producer (several 
 times if needed). Every time we run the producer, we'll see in the logs how the producer pushes
 the messages, and how the consumer consumes them.

## Infrastructure

The *docker-compose.yml* defines a basic Kafka installation with only one server/broker:

```
version: '2'

networks:
  kafka-net:
    driver: bridge

services:
  zookeeper-server:
    image: 'bitnami/zookeeper:latest'
    networks:
      - kafka-net
    ports:
      - '2181:2181'
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
  kafka-server1:
    image: 'bitnami/kafka:latest'
    networks:
      - kafka-net
    ports:
      - '9092:9092'
      - '9093:9093'
    environment:
      - KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181
      - KAFKA_CFG_LISTENERS=INTERNAL://:9093,EXTERNAL://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=INTERNAL://kafka-server1:9093,EXTERNAL://localhost:9092
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
      - KAFKA_CFG_INTER_BROKER_LISTENER_NAME=INTERNAL
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper-server
``` 
 ## Pre-requisites
 
 * The Kafka server must be running, and the port its listening to must be compatible with the
 configuration stored in the `application.properties` in this example.
 
