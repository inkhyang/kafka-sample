package com.inkhyang.storage.kafka.producer;

import com.inkhyang.base.utils.ProductAppConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic confirms() {
        return TopicBuilder.name(ProductAppConstants.CONFIRM_TOPIC_NAME)
                .partitions(3)
                .build();
    }
    @Bean
    public NewTopic notConfirms() {
        return TopicBuilder.name(ProductAppConstants.NOT_CONFIRM_TOPIC_NAME)
                .partitions(3)
                .build();
    }
}
