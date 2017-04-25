package es.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Configuramos los servicios Rest con el filtro Cors para que se puedan realizar peticiones desde otros servidores
 *
 * @author jaime.agudo
 *
 */
@Configuration
public class RestConfiguration {

	    @Bean
	    public CorsFilter corsFilter() {

	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        final CorsConfiguration config = new CorsConfiguration();
	        config.setAllowCredentials(true);
	        config.addAllowedOrigin("*");
	        config.addAllowedHeader("*");
	        config.addAllowedMethod("GET");
	        config.addAllowedMethod("PUT");
	        source.registerCorsConfiguration("/**", config);
	        return new CorsFilter(source);
	    }
}
