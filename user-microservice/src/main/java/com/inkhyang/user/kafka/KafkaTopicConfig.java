package com.inkhyang.user.kafka;

import com.inkhyang.base.utils.AppConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic documents() {
        return TopicBuilder.name(AppConstants.TOPIC_NAME)
                .partitions(3)
                .compact()
                .build();
    }
}
