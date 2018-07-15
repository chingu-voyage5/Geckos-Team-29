package com.geckos.springmvc.auth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.geckos.springmvc.entity.Employee;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthenticatedProfile implements Authentication {
	
	private final Employee employee;

    public JwtAuthenticatedProfile(Employee employee) {
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

    
}