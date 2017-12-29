kafka-connect-hello
-------------------

Example [Kafka Connect](http://kafka.apache.org/documentation/#connect) sink connector and transform.

## walkthrough

Build the project

```
gradle assemble
```

Run the zookeeper, broker, and connect worker.

```
docker-compose up
```

Run the _stdout-sink_ connector

```
curl -XPOST http://localhost:8083/connectors \
  -H "Content-Type: application/json" \
  -d '{
    "name": "stdout-sink",
    "config": {
      "connector.class": "org.hello.StdoutSinkConnector",
      "tasks.max": 1,
      "topics": "hello-logs",
      "transforms": "hello",
      "transforms.hello.type": "org.hello.HelloTransformation"
    }
  }'
```

Write messages to the `hello-logs` topic

```
docker exec -it hello-broker /usr/bin/kafka-console-producer \
  --broker-list localhost:9092 \
  --topic hello-logs
```
