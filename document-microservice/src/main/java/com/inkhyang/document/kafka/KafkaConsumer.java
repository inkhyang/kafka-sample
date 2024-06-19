package com.inkhyang.document.kafka;

import com.inkhyang.base.dto.user.UserDto;
import com.inkhyang.base.utils.AppConstants;
import com.inkhyang.document.application.service.UserService;
import com.inkhyang.document.dto.UserDtoMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;


    @KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID,
            containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserDto userDto) {
        userService.saveUser(userDtoMapper.toDomain(userDto));
        LOGGER.info(String.format("Message received -> %s", userDto));
    }

    /*@KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }*/
}
