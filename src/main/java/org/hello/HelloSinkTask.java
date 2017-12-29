package org.hello;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HelloSinkTask extends SinkTask {
  private static final Logger log = LoggerFactory.getLogger(HelloSinkTask.class);

  @Override
  public String version() {
    return Version.getVersion();
  }

  @Override
  public void start(Map<String, String> props) {
    log.info("Starting HelloSinkTask.");
  }

  @Override
  public void open(Collection<TopicPartition> partitions) {
    log.debug("Opening the task for topic partitions: {}", partitions);
    Set<String> topics = new HashSet<>();
    for (TopicPartition tp : partitions) {
      topics.add(tp.topic());
    }
  }

  @Override
  public void put(Collection<SinkRecord> records) throws ConnectException {
    log.trace("Putting data records {}", records);
  }

  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> offsets) {
    log.trace("Flushing data with the following offsets: {}", offsets);
  }

  @Override
  public void close(Collection<TopicPartition> partitions) {
    log.debug("Closing the task for topic partitions: {}", partitions);
  }

  @Override
  public void stop() throws ConnectException {
    log.info("Stopping HelloSinkTask.");
  }
}
