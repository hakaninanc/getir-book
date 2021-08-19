package com.getir.book.util.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final GetirBookProperties getirBookProperties;

    public String generateUserToken(String tokenKey) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + getirBookProperties.getExpirationInMs());
        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, getirBookProperties.getSecretKey())
                .claim("uniqueTokenKey", tokenKey)
                .compact();
    }

    public boolean validateToken(String authToken) {
        if (Objects.isNull(authToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(getirBookProperties.getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    public String getTokenIdentification(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getirBookProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

}
