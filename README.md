
# learning Kafka

A series of small examples and *Proof Of Concepts* on how to use Kafka. Each subproject comes with its own 
*README.MD* file, andalso a *docker-compose* file with the definition of the infraestructure needed 
to run them.


## Software Requirements

* Docker
* [Conduktor (Kafka client)](https://www.conduktor.io/download/)
* the *lombok* library is used in the proejct, so maku sure your IDE is aware of it. In IntellijIDEA, 
go to *Preferences.../Build, Execiution, Deployment/Compiler/Annotation Processors*, and active the 
option *Enable annotation processing* and *Obtain processors from classpath*.

# Examples

* [Basic example](basic/README.md): A very basic example of a Producer & Consumer
* [Custom Serialization](serializationCustom/README.md): An example where the data exchanged is not a simple
String, but POJOS, which are serialized with custom Classes.
* [Avro Serialization](serializationAvro/README.md): An example where the data exchange is also POJO, but 
is serialized using the *Avro* libraries.


## Example-1: Basic example

This basic example is composed of a *Producer* and a *Consumer*, pushing messages in the form of String messages
to a Kafka Topic. no custom Serialization is used here.

In order to run the Kafka infrastructure, you need to run the `docker-compose-single.yml` in the project folder.
It contains the definition of the services needed to run Kafka:

* **Zookepper**: This is a server used to coordinate the different Consumers and cluster within a Kafka installation.
* **kafka-server1**: A definition of one Kafka *broker*


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

## Example-2: Custom Serialization

this example builds on top of the previous example, but in this case we are not consuming String messages, but using
a POJO class instead. So no some serialization needs to be performed on those POJO, and we have several alternatives:

* A custom Serialization, implementing our own serializers
* Using the *Avro* Serialization framework (recommended by the creators of Kafka)
* Using *protobuffers*

The infraestructure for this Example is defined in the `docker-composore-single.yml` in this project folder, which
builds on top of the ficle from the prvious example, but in this case we are adding a new service which is the 
*schema registry* used by the *avro* library:


```

```

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/gradle-plugin/reference/html/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

