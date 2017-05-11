package es.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Clase principal
 *
 * @author jaime.agudo
 *
 */
@Configuration
@ComponentScan
@EnableResourceServer
@EnableAutoConfiguration
public class ServicesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(ServicesApplication.class);
	}

	public static void main(final String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}
}
