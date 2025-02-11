package org.example.testtask.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.testtask.security.JwtAuthenticationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {
    private static final Logger logger = LogManager.getLogger(JwtService.class);

    @Value("${org.example.testtask.jwt.secret}")
    private String jwtSecret;

    public JwtAuthenticationDto generateAuthToken(String username) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateJwtToken(username));
        jwtDto.setRefreshToken(generateRefreshToken(username));
        return jwtDto;
    }

    public JwtAuthenticationDto refreshBaseToken(
            String username,
            String refreshToken
    ) {
        JwtAuthenticationDto jwtDto = new JwtAuthenticationDto();
        jwtDto.setToken(generateJwtToken(username));
        jwtDto.setRefreshToken(refreshToken);
        return jwtDto;
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch(ExpiredJwtException e) {
            logger.error("Expired jwt exception", e);
        } catch (UnsupportedJwtException e) {
            logger.error("unsupported jwt exception", e);
        } catch (MalformedJwtException e) {
            logger.error("malformed jwt exception", e);
        } catch (SecurityException e) {
            logger.error("security exception", e);
        } catch (Exception e) {
            logger.error("invalid token", e);
        }
        return false;
    }

    private String generateJwtToken(String username) {
        Date date = Date
                .from(LocalDateTime.now()
                        .plusMinutes(10).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .subject(username)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private String generateRefreshToken(String username) {
        Date date = Date
                .from(LocalDateTime.now()
                        .plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .subject(username)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
