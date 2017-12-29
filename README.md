kafka-connect-hello
-------------------

Example Kafka Connect Sink and Transform.

## walkthrough

Build the project

```
gradle assemble
```

Run the Zookeeper, Broker, and Connect worker.

```
docker-compose up
```

Run the _stdout-sink_ connector

```
curl -XPOST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d '{"name": "hello-sink", "config": {"connector.class": "org.hello.StdoutSinkConnector", "tasks.max": 1, "topics": "hello-logs"}}'
```

Write messages to the `hello-logs` topic

```
docker exec -t hello-broker /usr/bin/kafka-console-producer --broker-list localhost:9092 --topic hello-logs
```
