package com.hht.cloud.webture.urlreceiver;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class UrlreceiverApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(UrlreceiverApplication.class, args);
	}
}
