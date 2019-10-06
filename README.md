<<<<<<< HEAD
# Spring Boot with Kafka Producer Example

This Project covers how to use Spring Boot with Spring Kafka to Publish JSON/String message to a Kafka topic
## Start Zookeeper
- `bin/zookeeper-server-start.sh config/zookeeper.properties`

## Start Kafka Server
- `bin/kafka-server-start.sh config/server.properties`

## Create Kafka Topic
- `bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic Kafka_Example`

## Consume from the Kafka Topic via Console
- `bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Kafka_Example --from-beginning`

=======
# rupesh
>>>>>>> e29298f607485151bb5941cafa3d37dd7a850adf
## End Points
http://localhost:8081/lowes/publish
http://localhost:8081/lowes/product/showAll
http://localhost:8081/lowes/product/show/{id}
http://localhost:8081/lowes/product/delete/{id}
