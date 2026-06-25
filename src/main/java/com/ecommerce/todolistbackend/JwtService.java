package com.ecommerce.todolistbackend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private String sign = "123456790234567890123456789012345678901234567890";
    private final Long expiration = 7*24*60*60*1000L;

    public String generateToken(UserRequest request, Integer id) {

            String compact = Jwts
                    .builder()
                    .subject(request.userName())
                    .signWith(secretKey())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + expiration))
                    .compact();
            return compact;

    }

    public boolean validateToken(String username, UserDetails userDetails,String token){
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extrackClaims(token)
                .getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public Claims extrackClaims(String token){
       return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(sign.getBytes());
    }

    public String extractUserName(String token) {
        return extrackClaims(token)
                .getSubject();

    }
}
