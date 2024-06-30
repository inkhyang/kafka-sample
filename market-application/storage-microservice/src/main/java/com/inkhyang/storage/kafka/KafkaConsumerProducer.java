package com.inkhyang.storage.kafka;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.dto.order.ProductDto;
import com.inkhyang.base.utils.ProductAppConstants;
import com.inkhyang.storage.application.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumerProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerProducer.class);
    private final StorageService storageService;
    private final KafkaTemplate<java.lang.String, Order> kafkaTemplate;
    private final Map<ProductDto, Integer> map = new HashMap<>();
    private final String status = "CONFIRMED";
    private final String statusN = "NOT_CONFIRMED";


    public KafkaConsumerProducer(StorageService storageService, KafkaTemplate<java.lang.String, Order> kafkaTemplate) {
        this.storageService = storageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = ProductAppConstants.ACCEPT_TOPIC_NAME,
            groupId = ProductAppConstants.GROUP_ID,
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consumeAndRedirect(Order order) {
        LOGGER.info("Message received {}", order);
        List<ProductDto> products = order.getProducts();
        extractedUnavailable(products);
        if (!map.isEmpty()) {
            order.setStatus(statusN);
            map.keySet().stream()
                    .map(p -> java.lang.String.format("no such product %s in amount %s", p, map.get(p)))
                    .forEach(LOGGER::info);
            kafkaTemplate.send(ProductAppConstants.NOT_CONFIRM_TOPIC_NAME, order);
            map.clear();
        } else {
            LOGGER.info("redirect order to payment {}", order);
            order.setStatus(status);
            kafkaTemplate.send(ProductAppConstants.CONFIRM_TOPIC_NAME, order);
            products.forEach(p -> storageService.decreaseProductAmtByArticle(p.article(), p.amt()));
        }
    }

    private void extractedUnavailable(List<ProductDto> products) {
        for (ProductDto product : products) {
            int non = storageService.getProductByArticle(product.article()).getAvailableAmt() - product.amt();
            if (non < 0) {
                map.put(product, Math.abs(non));
            }
        }
    }
}
