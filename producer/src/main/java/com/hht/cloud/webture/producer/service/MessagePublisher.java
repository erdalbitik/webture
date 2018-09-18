package com.hht.cloud.webture.producer.service;

import com.hht.cloud.webture.producer.RabbitMqConfig;
import com.hht.cloud.webture.producer.model.ScreenshotRequest;
import com.hht.cloud.webture.producer.model.ScreenshotResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendQueueMessage(ScreenshotRequest screenshotRequest) {
        return sendQueueMessage(RabbitMqConfig.QUEUE_MESSAGES, screenshotRequest);
    }

    /**
     * push message to the queue
     * @param queueName name of target queue
     * @param message message to be sent
     * @return true if successful, otherwise false
     */
    private boolean sendQueueMessage(String queueName, ScreenshotRequest message) {
        try {
            LOGGER.info(String.format("send message [%s] to queue [%s]", message, queueName));
            LOGGER.info(String.format("sendQueueMessage @ sendQueueMessage <" + message + ">"));

            rabbitTemplate.convertAndSend(queueName, message);
            return true;
        } catch (Exception e) {
            LOGGER.error("failed!", e);
            return false;
        }
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_MESSAGES)
    public void handleMessage(final ScreenshotResponse sr) {
        LOGGER.info("Response Message Received - MessageId is: "+ sr.getMessageId());
        LOGGER.info("Response Message Received - URL is: "+ sr.getUrl());
        LOGGER.info("Response Message Received - Screenshot URL is: "+ sr.getScreenshotUrl());

    }


}
