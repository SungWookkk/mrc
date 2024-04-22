package MrcProject6.MrcProject6.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration.ms}")
    private long expirationMs;

    // Generate JWT token
    public String generateToken(String userNick) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        // JWT 토큰 생성 시 사용할 서명 키 생성
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Jwts.builder()
                .setSubject(userNick)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    // Extract user nickname from token
    public String extractUserNickFromToken(String token) {
        try {
            logger.info("Extracting user nickname from token: {}", token);
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token).getBody();
            String userNick = claims.getSubject();
            logger.info("User nickname extracted successfully: {}", userNick);
            return userNick;
        } catch (Exception e) {
            logger.error("Error extracting user nickname from token: {}", e.getMessage());
            return null;
        }
    }
}
