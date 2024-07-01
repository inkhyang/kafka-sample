package com.inkhyang.storage.kafka.producer;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.utils.ProductAppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final String status = "CONFIRMED";
    private final String statusN = "NOT_CONFIRMED";

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    public void sendMessage(boolean isValid, Order order) {
        LOGGER.info("Message received to producer {}", order);
        if (isValid) {
            order.setStatus(status);
            kafkaTemplate.send(ProductAppConstants.CONFIRM_TOPIC_NAME, order);
        } else {
            order.setStatus(statusN);
            kafkaTemplate.send(ProductAppConstants.NOT_CONFIRM_TOPIC_NAME, order);
        }
    }

}
