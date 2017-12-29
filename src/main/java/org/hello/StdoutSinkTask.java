package org.hello;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StdoutSinkTask extends SinkTask {
  private static final Logger log = LoggerFactory.getLogger(StdoutSinkTask.class);

  @Override
  public String version() {
    return Version.getVersion();
  }

  @Override
  public void start(Map<String, String> props) {
    log.info("Starting StdoutSinkTask.");
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
    for (SinkRecord record: records) {

      Object key = record.key();
      if (key != null) {
        System.out.print(key.toString());
        System.out.print(" | ");
      }

      Object value = record.value();
      if (value == null) {
        System.out.println("-");
      } else {
        System.out.println(value.toString());
      }
    }
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
    log.info("Stopping StdoutSinkTask.");
  }
}
