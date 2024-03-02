package uz.pdp.eufloria.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.common.AppConstants;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    @Value("${spring.security.jwt.token.expiration}")
    private long expiration;

    @Value("${spring.security.jwt.token.secret}")
    private String secret;

    public String generateToken(UUID userId) {
        Date now = new Date();
        return AppConstants.TOKEN_TYPE + Jwts.builder()
                .setSubject(String.valueOf(userId))
                .signWith(signingKey())
                .setIssuedAt(now)
                .setExpiration(Date.from(now.toInstant().plusSeconds(expiration)))
                .compact();
    }

    public String claimFromToken(String token) {
        if (token.startsWith(AppConstants.TOKEN_TYPE))
            token = token.substring(AppConstants.TOKEN_TYPE.length());

        return Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private Key signingKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }
}
