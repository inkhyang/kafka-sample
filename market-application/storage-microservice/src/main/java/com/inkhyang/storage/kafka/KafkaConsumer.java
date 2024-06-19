package com.inkhyang.storage.kafka;

import com.inkhyang.base.dto.order.OrderDto;
import com.inkhyang.base.dto.order.ProductDto;
import com.inkhyang.base.utils.ProductAppConstants;
import com.inkhyang.storage.application.StorageService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private final StorageService storageService;


    @KafkaListener(topics = ProductAppConstants.TOPIC_NAME,
            groupId = ProductAppConstants.GROUP_ID,
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(OrderDto orderDto) {
        List<ProductDto> products = orderDto.products();
        products.forEach(p -> storageService.decreaseProductAmtByArticle(p.article(), p.amt()));
        LOGGER.info(String.format("Message received -> %s", orderDto));
    }

    /*@KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID,
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        LOGGER.info(String.format("Message received -> %s", message));
    }*/
}
