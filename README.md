# POC-Kafka-Consumer-Spring

## Setting up Kafka’s Docker

Clone the [Kafka’s git project](https://github.com/wurstmeister/kafka-docker) and initialize the docker image.

    git clone https://github.com/wurstmeister/kafka-docker.git

   Update KAFKA_ADVERTISED_HOST_NAME inside 'docker-compose.yml', For example, set it to 172.17.0.1

Start the docker container

    docker-compose up -d
   Optional - Scale the cluster by adding more brokers (Will start a single zookeeper instance)

    docker-compose scale kafka=3
    
  ## Kafka Shell

To start it just run the command:

     ./start-kafka-shell.sh <DOCKER_HOST_IP/KAFKA_ADVERTISED_HOST_NAME>

 For example case:  

     ./start-kafka-shell.sh 172.17.0.1
## Create a Topic

From within the Kafka Shell, run the following to create and describe a topic:

     >$KAFKA_HOME/bin/kafka-topics.sh --create --topic test \  
    --partitions 4 --replication-factor 2 \  
    --bootstrap-server `broker-list.sh`
  
     >$KAFKA_HOME/bin/kafka-topics.sh --describe --topic test \  
    --bootstrap-server `broker-list.sh`

## Producer

Initialize the producer and write messages to Kafka’s brokers.

    > $KAFKA_HOME/bin/kafka-console-producer.sh --topic=test \  
    --broker-list=`broker-list.sh`  
    >> Hello World!  
    >> I'm a Producer writing to 'hello-topic'

## Consumer

Initialize the consumer from another Kafka terminal and it will start reading the messages sent by the producer.

    > $KAFKA_HOME/bin/kafka-console-consumer.sh --topic=test \  
    --from-beginning --bootstrap-server `broker-list.sh`

