package com.hht.cloud.webture.producer.service;

import org.springframework.stereotype.Service;

@Service
public class MessagePublisher {
	/*private static final Logger LOGGER = LoggerFactory.getLogger(MessagePublisher.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean sendQueueMessage(ScreenshotRequest screenshotRequest) {
        return sendQueueMessage(RabbitMqConfig.QUEUE_MESSAGES, screenshotRequest);
    }

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
*/

}
