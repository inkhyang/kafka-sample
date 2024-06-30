package com.inkhyang.commerce.kafka;

import com.inkhyang.base.dto.order.Order;
import com.inkhyang.base.dto.order.OrderDto;
import com.inkhyang.base.dto.user.VerificatedUserDto;
import com.inkhyang.base.utils.ProductAppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final String status = "ACCEPTED";

    @Autowired
    private KafkaTemplate<java.lang.String, Order> kafkaTemplate;

    public void sendMessage(OrderDto orderDto) {
        Order order = new Order(orderDto.userDto(), orderDto.products(), status);

        kafkaTemplate.send(ProductAppConstants.ACCEPT_TOPIC_NAME,
                order);
        VerificatedUserDto user = orderDto.userDto();
        LOGGER.info("Sent: {}", orderDto);
        orderDto.products().stream()
                .map(p -> java.lang.String.format("Product article -> %s \n price %s in amount %s purchase price %s",
                p.article(), p.price(), p.amt(), p.price() * p.amt()))
                .forEach(LOGGER::info);
    }
}
