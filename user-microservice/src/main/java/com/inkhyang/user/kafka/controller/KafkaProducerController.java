package com.inkhyang.user.kafka.controller;

import com.inkhyang.base.dto.user.UserDto;
import com.inkhyang.user.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
@AllArgsConstructor
public class KafkaProducerController {
    private final KafkaProducer kafkaProducer;

    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody UserDto userDto){
        kafkaProducer.sendMessage(userDto);
        return ResponseEntity.ok("Message sent to kafka topic");
    }

    /*@GetMapping("/publish/message")
    public ResponseEntity<String> publish(@RequestParam String message){
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }*/
}
