package com.inkhyang.payment.kafka;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.dto.order.ProductDto;
import com.inkhyang.base.utils.ProductAppConstants;
import com.inkhyang.payment.service.PaymentService;
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
    private final PaymentService paymentService;


    @KafkaListener(topics = ProductAppConstants.CONFIRM_TOPIC_NAME,
            groupId = ProductAppConstants.GROUP_ID,
            containerFactory = "orderKafkaListenerContainerFactory")
    public void consume(Order order) {
        LOGGER.info("Order received to pay -> {}", order);
        paymentService.payment(order);
        List<ProductDto> products = order.getProducts();
    }
}
