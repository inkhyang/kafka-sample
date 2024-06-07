package com.inkhyang.user.kafka;

import com.inkhyang.base.dto.UserDto;
import com.inkhyang.base.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private AtomicInteger ai = new AtomicInteger();

    @Autowired
    private KafkaTemplate<String, UserDto> kafkaTemplate;

   /* @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;*/

    public void sendMessage(UserDto userDto) {
        kafkaTemplate.send(AppConstants.TOPIC_NAME,
                String.valueOf(ai.incrementAndGet()),
                userDto);
        LOGGER.info(String.format("Message sent -> %s", userDto.name()));
    }

    /*public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        stringKafkaTemplate.send(AppConstants.TOPIC_NAME,
                String.valueOf(ai.incrementAndGet()),
                message);
    }*/
}
