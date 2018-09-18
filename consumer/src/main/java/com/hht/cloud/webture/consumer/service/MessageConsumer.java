package com.hht.cloud.webture.consumer.service;

import com.hht.cloud.webture.consumer.RabbitMqConfig;
import com.hht.cloud.webture.consumer.model.ScreenshotRequest;
import com.hht.cloud.webture.consumer.model.ScreenshotResponse;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;


@Component
public class MessageConsumer {

    static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_MESSAGES)
    public void handleMessage(final ScreenshotRequest sr) {
        logger.info("Message Received - URL is: "+ sr.getUrl());
        logger.info("Message Received - MessageId is: "+ sr.getMessageId());

        ScreenshotResponse screenshotResponse = new ScreenshotResponse();
        screenshotResponse.setMessageId(sr.getMessageId());
        screenshotResponse.setUrl(sr.getUrl());
        String uploadedUrl = takeScreenshot(sr.getUrl());

        screenshotResponse.setScreenshotUrl(uploadedUrl);
        logger.info("Message Post : "+ screenshotResponse);

        rabbitTemplate.convertAndSend(RabbitMqConfig.QUEUE_MESSAGES, screenshotResponse);

    }

    public static String takeScreenshot(String url) {

        try {

            String path ="";

            final File screenShot = new File("screenshot.png").getAbsoluteFile();

            final WebDriver driver = new FirefoxDriver();
            try {

                driver.get(url);

                TimeUnit.SECONDS.sleep(5);

                final File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(outputFile, screenShot);

                path = screenShot.getAbsolutePath();
                logger.info("File @  ", path);

            } catch(Exception ex) {
                logger.error("Exception @ takeScreenshot: ", ex);
            }
            finally
            {   driver.close();   }

            return path;
        } catch(Exception ex) {
            logger.error("Exception @ takeScreenshot: ", ex);
        }

        return null;

    }

}
