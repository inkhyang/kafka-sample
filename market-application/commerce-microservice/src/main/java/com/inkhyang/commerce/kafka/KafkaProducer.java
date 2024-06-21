package com.inkhyang.commerce.kafka;

import com.inkhyang.base.dto.order.OrderDto;
import com.inkhyang.base.dto.order.ProductDto;
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

    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;


    public void sendMessage(OrderDto orderDto) {
        kafkaTemplate.send(ProductAppConstants.TOPIC_NAME,
                orderDto);
        VerificatedUserDto user = orderDto.userDto();
        LOGGER.info(String.format("\n Order details: \n Username: %s \n Id card: %s",
                user.name(), user.idCard()));
        double totalPrice = 0;
        for (ProductDto p : orderDto.products()) {
            totalPrice += p.price();
            LOGGER.info(String.format("Product article -> %s \n price %s in amount %s purchase price %s",
                    p.article(), p.price(), p.amt(), p.price() * p.amt()));
        }
        LOGGER.info(String.format("total price: %s", totalPrice));

    }
}
