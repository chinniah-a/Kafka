Kafka Setup

Prerequesites
1) Download Apache Kafka 
2) start the Zookeeper Server first
in cmd you can type ".\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties"
3) start the Kafka Server
in cmd you can type ".\bin\windows\kafka-server-start.bat .\config\server.properties"
4) create a topic of your choice, make appropriate changes in application.properties file for the topic name you created
in cmd, traverse to /bin/windows and execute the following command 
"kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test"
Modify the replication factor and partitions as needed

You can import the project as a maven project and run the Kafka main application. You can hit the RestAPI to send messages to the topic

There is Kafka Listener set up, which will listen the topic for which any messages have been published by the producer



