package com.hht.cloud.webture.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class ConsumerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) {

        LOGGER.info(String.format("--CONSUMERAPPLICATION-- INFO"));
        LOGGER.warn(String.format("--CONSUMERAPPLICATION-- WARN"));
        LOGGER.debug(String.format("--CONSUMERAPPLICATION-- DEBUG"));
        LOGGER.error(String.format("--CONSUMERAPPLICATION-- ERROR"));

        SpringApplication.run(ConsumerApplication.class, args);
    }
}
