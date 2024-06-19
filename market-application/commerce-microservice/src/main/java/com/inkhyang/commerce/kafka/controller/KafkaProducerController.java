package com.inkhyang.commerce.kafka.controller;

import com.inkhyang.base.dto.order.OrderDto;
import com.inkhyang.commerce.application.ControllerService;
import com.inkhyang.commerce.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
@AllArgsConstructor
public class KafkaProducerController {
    private final ControllerService controllerService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody OrderDto orderDto) {
        orderDto.products().forEach(o -> controllerService.sell(o, orderDto.userDto()));
        kafkaProducer.sendMessage(orderDto);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
