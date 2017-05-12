package es.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

/**
 * Clase principal
 *
 * @author jaime.agudo
 *
 */
@SpringBootApplication
public class ServicesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(ServicesApplication.class);
	}

	public static void main(final String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

	@Autowired
	public void authenticationManager(final AuthenticationManagerBuilder builder) throws Exception {

		/*
		 * builder.userDetailsService(new UserDetailsService() {
		 *
		 * @Override public UserDetails loadUserByUsername(final String s)
		 * throws UsernameNotFoundException { final Usuario usuario = new
		 * Usuario(); usuario.setUsername(s); usuario.setPassword("cambiame");
		 * usuario.setActivo(true); return usuario; } });
		 */

		final DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(
				"ldap://ldappre.map.es:389");
		contextSource.setUserDn("cn=admin,dc=map,dc=es");
		contextSource.setPassword("cambiame");
		contextSource.setReferral("follow");
		contextSource.setBase("dc=map,dc=es");
		contextSource.afterPropertiesSet();

		final LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProviderConfigurer = builder
				.ldapAuthentication();

		ldapAuthenticationProviderConfigurer.userSearchFilter("uid={0}").userSearchBase("")
				.contextSource(contextSource);

	}
}
