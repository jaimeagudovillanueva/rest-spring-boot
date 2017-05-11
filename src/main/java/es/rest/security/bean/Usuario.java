package es.rest.security.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Usuario implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean activo;

	public void setUsername(final String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setActivo(final boolean activo) {
		this.activo = activo;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = new ArrayList<>();
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return activo;
	}

	@Override
	public boolean isAccountNonLocked() {
		return activo;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return activo;
	}

	@Override
	public boolean isEnabled() {
		return activo;
	}
}
