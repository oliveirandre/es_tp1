/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author danielmartins
 */
@Service
public class Consumer {

    public Consumer() {}
    
    private final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "Weather_Topic";

    @KafkaListener(topics = TOPIC, groupId = "group_weather")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
    
}
