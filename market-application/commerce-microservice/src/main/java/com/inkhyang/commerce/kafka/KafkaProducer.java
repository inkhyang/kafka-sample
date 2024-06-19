package com.inkhyang.commerce.kafka;

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

    @Autowired
    private KafkaTemplate<String, OrderDto> kafkaTemplate;


    public void sendMessage(OrderDto orderDto) {
        kafkaTemplate.send(ProductAppConstants.TOPIC_NAME,
                orderDto);
        VerificatedUserDto user = orderDto.userDto();
        LOGGER.info(String.format("\n Order details: \n Username: %s \n Id card: %s",
                user.name(), user.idCard()));
        orderDto.products().forEach(p ->
                        LOGGER.info(String.format("Product article -> %s in amount %s",
                        p.article(), p.amt())));

    }
}
