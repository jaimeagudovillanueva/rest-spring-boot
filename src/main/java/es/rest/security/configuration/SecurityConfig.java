/*
 * package es.rest.security.configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.authentication.configurers.
 * ldap.LdapAuthenticationProviderConfigurer; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.ldap.DefaultSpringSecurityContextSource;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(final HttpSecurity http) throws Exception
 * {
 * 
 * http.authorizeRequests().anyRequest().authenticated().and().csrf().disable();
 * }
 * 
 * @Autowired public void configureGlobal(final AuthenticationManagerBuilder
 * auth) throws Exception { final DefaultSpringSecurityContextSource
 * contextSource = new DefaultSpringSecurityContextSource(
 * "ldap://ldappre.map.es:389");
 * contextSource.setUserDn("cn=admin,dc=map,dc=es");
 * contextSource.setPassword("cambiame"); contextSource.setReferral("follow");
 * contextSource.setBase("dc=map,dc=es"); contextSource.afterPropertiesSet();
 * 
 * final LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder>
 * ldapAuthenticationProviderConfigurer = auth .ldapAuthentication();
 * 
 * ldapAuthenticationProviderConfigurer.userSearchFilter("uid={0}").
 * userSearchBase("") .contextSource(contextSource); } }
 */