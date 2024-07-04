package com.inkhyang.storage.kafka;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.utils.ProductAppConstants;
import com.inkhyang.storage.application.StorageService;
import com.inkhyang.storage.kafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final StorageService storageService;
    private final KafkaProducer kafkaProducer;
    private final String status = "CONFIRMED";
    private final String statusN = "NOT_CONFIRMED";


    public KafkaConsumer(StorageService storageService, KafkaTemplate<String, Order> kafkaTemplate, KafkaProducer kafkaProducer) {
        this.storageService = storageService;
        this.kafkaProducer = kafkaProducer;
    }

    @KafkaListener(topics = ProductAppConstants.ACCEPT_TOPIC_NAME,
            groupId = ProductAppConstants.GROUP_ID,
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(Order order) {
        LOGGER.info("Message received {}", order);
        boolean isValid = storageService.process(order.getProducts());
        kafkaProducer.sendMessage(isValid, order);
        LOGGER.info(String.format("Redirected to producer with confirmed: %s status", isValid));
    }
}
