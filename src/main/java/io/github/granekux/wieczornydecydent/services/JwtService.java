package io.github.granekux.wieczornydecydent.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    // Wstrzykujemy wartości z application.properties
    private final String jwtSecretKey;
    private final long jwtExpirationMs;

    public JwtService(
            @Value("${jwt.secret}") String jwtSecretKey,
            @Value("${jwt.expiration.ms}") long jwtExpirationMs
    ) {
        this.jwtSecretKey = jwtSecretKey;
        this.jwtExpirationMs = jwtExpirationMs;
    }

    /**
     * Główna funkcja - generuje token JWT dla podanej nazwy użytkownika (email).
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // W przyszłości możesz tu dodać np. role użytkownika
        // claims.put("roles", userDetails.getAuthorities());

        return createToken(claims, username);
    }

    /**
     * Metoda pomocnicza do tworzenia tokenu.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .claims(claims) // Ustawiamy "claims" (dane w tokenie)
                .subject(subject) // Ustawiamy "subject" (kogo token dotyczy, np. email)
                .issuedAt(now) // Ustawiamy czas wydania
                .expiration(expirationDate) // Ustawiamy czas wygaśnięcia
                .signWith(getSigningKey(), Jwts.SIG.HS256) // Podpisujemy kluczem
                .compact();
    }

    /**
     * Zwraca klucz do podpisywania tokenów na podstawie sekretu z properties.
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // --- Metody do walidacji tokenu (przydadzą się za chwilę) ---

    /**
     * Wyciąga nazwę użytkownika (email) z tokenu.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Sprawdza, czy token jest ważny (nie wygasł i jest poprawnie podpisany).
     */
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Ta metoda parsuje token i weryfikuje jego podpis
        // Jeśli podpis jest zły lub token wygasł, rzuci wyjątek
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
