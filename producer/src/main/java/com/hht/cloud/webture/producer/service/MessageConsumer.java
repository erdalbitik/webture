package com.hht.cloud.webture.producer.service;

import com.hht.cloud.webture.producer.RabbitMqConfig;
import com.hht.cloud.webture.producer.model.FileProcess;
import com.hht.cloud.webture.producer.model.ScreenshotResponse;
import com.hht.cloud.webture.producer.repository.FileProcessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private FileProcessRepository repository;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_MESSAGES)
    public void handleMessage(final ScreenshotResponse sr) {

        logger.info("Message Received : "+ sr);

        logger.info("Message Received - URL is: "+ sr.getUrl());
        logger.info("Message Received - MessageId is: "+ sr.getMessageId());
        logger.info("Message Received - Screenshot URL is: "+ sr.getScreenshotUrl());

        FileProcess fp = repository.findByMessageId(sr.getMessageId());

        fp.setFileUrl(sr.getScreenshotUrl());
        repository.save(fp);

    }

}
