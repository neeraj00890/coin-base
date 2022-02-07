package com.orderbook.manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
public class SpringConfiguration {

	@Bean
	public ServletServerContainerFactoryBean servletServerContainerFactoryBean() {
	    ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
	    container.setMaxTextMessageBufferSize(327682354);
	    container.setMaxBinaryMessageBufferSize(327684355);
	    return container;
	}
}
