package com.binode.clientapp;

import com.binode.clientapp.dto.DepositRequest;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender<T> {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, T message) throws JsonProcessingException {
       try {
           ObjectMapper mapper = new ObjectMapper();
           String msgtoString = mapper.writeValueAsString(message);
           kafkaTemplate.send(topic, msgtoString);
       }
       catch (JsonGenerationException e) {
           System.out.println("Couldn't parse message");
       }



    }
}
