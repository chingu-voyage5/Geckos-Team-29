package com.geckos.springmvc.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.geckos.springmvc.entity.Employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {
    String secretKey = "parole";
    
    @Autowired
	private EmployeeService employeeService;
 
    public String tokenFor(Employee employee) throws IOException, URISyntaxException {
        
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject("chingu.io")
                .setExpiration(expiration)
                .setIssuer("gecko29")
                .claim("email", employee.getEmail())
                .claim("isAdmin",employee.isAdmin())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Employee verify(String token) throws IOException, URISyntaxException {
    	System.out.println("verify " +token);
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        System.out.println(claims.getBody());
        return employeeService.getEmployeeByEmail(claims.getBody().get("email").toString());
    }
}