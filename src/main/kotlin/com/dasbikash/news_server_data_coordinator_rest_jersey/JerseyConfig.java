package com.dasbikash.news_server_data_coordinator_rest_jersey;

import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		super();
		register(JacksonXMLProvider.class);
		packages("com.dasbikash.news_server_data_coordinator_rest_jersey.jersey");
	}
}
