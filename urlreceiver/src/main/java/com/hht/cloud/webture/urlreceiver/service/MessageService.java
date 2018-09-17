package com.hht.cloud.webture.urlreceiver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hht.cloud.webture.urlreceiver.model.FileProcess;

@Service
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    public boolean sendQueueMessage(FileProcess message) {
        String queueName = "data-queue";
        String jsonMessage = null;
        try {
        	jsonMessage = objectMapper.writeValueAsString(message);
		} catch (JsonProcessingException e) {
			LOGGER.error("Json Parse Problem: ", e);
			return false;
		}
        
        return sendQueueMessage(queueName, jsonMessage);
}

    /**
     * push message to the queue
     * @param queueName name of target queue
     * @param message message to be sent
     * @return true if successful, otherwise false
     */
    private boolean sendQueueMessage(String queueName, String message) {
        try {
            LOGGER.info(String.format("send message [%s] to queue [%s]", message, queueName));
            rabbitTemplate.convertAndSend(queueName, message);
            return true;
        } catch (Exception e) {
            LOGGER.error("failed!", e);
            return false;
        }
    }
}
