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
 
 ## Pre-requisites
 
 * The Kafka server must be running, and the port its listening to must be compatible with the
 configuration stored in the `application.properties` in this example.