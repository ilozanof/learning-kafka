
# learning Kafka

## Software Used

* Docker
* [Conduktor (Kafka client)](https://www.conduktor.io/download/)

## Install and run Kafka (docker)

### Single broker configuration

The `docker-compose-single.yml` in the main folder contains the definition of the services needed to run Kafka:

* **Zookepper**: This is a server used to coordinate the different Consumers and cluster within a Kafka installation.
* **kafka-server1**: A definition of one Kafka *broker*

The infraestructure for a single-broker Kafka configuration is defined in the `docker-compose-single.yml` file:

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

Note that we had to define 2 listeners for the only broker that we have (*kafka-server1*). The *INTERNAL* listener will accept 
connections from within the Container, and the *EXTERNAL* will accept connections from OUTSIDE the Docker container 
(like our Host Machine) 

Type the following command to download and start the infrastructure:

`docker-compose -f docker-compose-single.yml up -d`

After this, we start the Client (Conduktor) and configure a new Connection to one single Broker, which
in our case is listening to the port 9092: we configure it pointing it to `localhost:9092`. 


This will show as the control panel in Conduktor where we can see Brokers, Topics, etc.

# Examples
Each example of use of Kafka is located in a separate Java sub-project.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

