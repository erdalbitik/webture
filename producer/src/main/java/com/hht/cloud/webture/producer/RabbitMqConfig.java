package com.hht.cloud.webture.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer {
	
	@Bean
	Queue dataQueue() {
		return new Queue("data-queue", true);
	}
	
	@Bean
	Queue turnQueue() {
		return new Queue("turn-queue", true);
	}

	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange("webture:fileProcess");
	}

	@Bean
	Binding dataBinding(@Qualifier("dataQueue") Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}
	
	@Bean
	Binding turnQueueDataBinding(@Qualifier("turnQueue") Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

	/**
	 * give Jackson ObjetMapper to RabbitMq for JSON Mapping
	 *
	 * @return
	 */
	@Bean
	DefaultMessageHandlerMethodFactory jsonMessageHandlerMethod() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(jackson2Converter());
		return factory;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
		rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(jsonMessageHandlerMethod());
}

	/*public static final String QUEUE_MESSAGES = "data-queue";
	public static final String QUEUE_DEAD_MESSAGES = "dead-messages-queue";
	public static final String EXCHANGE_MESSAGES = "webture:fileProcess";

	@Bean
	Queue messagesQueue() {

		return QueueBuilder.durable(QUEUE_MESSAGES)
				.withArgument("x-dead-letter-exchange", "")
				.withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
				.withArgument("x-message-ttl", 5000)
				.build();
	}

	@Bean
	Queue deadLetterQueue() {
		return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
	}

	@Bean
	Exchange messagesExchange() {
		return ExchangeBuilder.topicExchange(EXCHANGE_MESSAGES).build();
	}

	@Bean
	Binding binding(Queue messagesQueue, TopicExchange messagesExchange) {
		return BindingBuilder.bind(messagesQueue).to(messagesExchange).with(QUEUE_MESSAGES);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	MessageHandlerMethodFactory messageHandlerMethodFactory() {
		DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
		messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
		return messageHandlerMethodFactory;
	}

	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
		return new MappingJackson2MessageConverter();
	}*/

}
