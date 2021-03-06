package org.hello;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StdoutSinkConnector extends SinkConnector {
  private Map<String, String> configProperties;

  @Override
  public String version() {
    return Version.getVersion();
  }

  @Override
  public void start(Map<String, String> props) throws ConnectException {
    configProperties = props;
  }

  @Override
  public Class<? extends Task> taskClass() {
    return StdoutSinkTask.class;
  }

  @Override
  public List<Map<String, String>> taskConfigs(int maxTasks) {
    List<Map<String, String>> taskConfigs = new ArrayList<>();
    Map<String, String> taskProps = new HashMap<>();
    taskProps.putAll(configProperties);

    for (int i = 0; i < maxTasks; i++) {
      taskConfigs.add(taskProps);
    }

    return taskConfigs;
  }

  @Override
  public void stop() throws ConnectException {
  }

  @Override
  public ConfigDef config() {
    return new ConfigDef();
  }
}
