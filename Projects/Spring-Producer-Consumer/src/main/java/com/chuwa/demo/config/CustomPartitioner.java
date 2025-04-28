package com.chuwa.demo.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * ClassName: CustomPartitioner
 * Package: com.chuwa.demo.config
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/28 16:53
 * @version 1.0
 */
public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes, Cluster cluster) {
        int partitionCount = cluster.partitionCountForTopic(topic);
        if (key == null) {
            return 0; // Always use the first partition if no key is provided
        }

        // Select partition based on key's hash code
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % partitionCount;
    }

    @Override
    public void close() {}

    @Override
    public void configure(Map<String, ?> configs) {}
}
