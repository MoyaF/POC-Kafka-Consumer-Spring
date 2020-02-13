## Setting up Kafka’s Docker

Clone the [Kafka’s git project](https://github.com/wurstmeister/kafka-docker) and initialize the docker image.

    git clone https://github.com/wurstmeister/kafka-docker.git

   You’ll need to edit the **docker-compose.yml** file so it looks like this:
   
    version: '2'
    services:
      zookeeper:
        image: wurstmeister/zookeeper:3.4.6
        ports:
         - "2181:2181"
      kafka:
        build: .
        ports:
         - "9092:9092"
        expose:
         - "9093"
        environment:
          KAFKA_ADVERTISED_LISTENERS: INSIDE://kafka:9093,OUTSIDE://localhost:9092
          KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: INSIDE:PLAINTEXT,OUTSIDE:PLAINTEXT
          KAFKA_LISTENERS: INSIDE://0.0.0.0:9093,OUTSIDE://0.0.0.0:9092
          KAFKA_INTER_BROKER_LISTENER_NAME: INSIDE
          KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
        volumes:
         - /var/run/docker.sock:/var/run/docker.sock
	    

Start the docker container

    docker-compose up -d
   Optional - Scale the cluster by adding more brokers (Will start a single zookeeper instance)

    docker-compose scale kafka=3
    
  ## Kafka Shell

To start it just run the command:

     docker exec -i -t -u root $(docker ps | grep docker_kafka | cut -d' ' -f1) /bin/bash

## Create a Topic

From within the Kafka Shell, run the following to create a topic:

    $KAFKA_HOME/bin/kafka-topics.sh --create --partitions 4 --bootstrap-server kafka:9092 --topic test

## Producer

Initialize the producer and write messages to Kafka’s brokers.

    $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list kafka:9092 --topic=test

## Consumer

Initialize the consumer from another Kafka terminal and it will start reading the messages sent by the producer.

    $KAFKA_HOME/bin/kafka-console-consumer.sh --from-beginning --bootstrap-server kafka:9092 --topic=test

