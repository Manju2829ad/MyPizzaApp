package basepackage.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    
    
    String publicPath="C:\\Users\\Manju\\MyProject\\MyPizza\\src\\main\\resources\\publicKey.pem";
    String privatePath="C:\\Users\\Manju\\MyProject\\MyPizza\\src\\main\\resources\\privateKey.pem";
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private long expirationTime;

    // Constructor to inject expiration time
    public JwtTokenUtil(@Value("${jwt.expiration.time:86400000}") long expirationTime) {
        this.expirationTime = expirationTime; // Default to 1 day in milliseconds

        // Load keys
        this.privateKey = loadKey(privatePath, this::getPrivateKey)
                .orElseThrow(() -> new RuntimeException("Failed to load private key"));
        this.publicKey = loadKey(publicPath, this::getPublicKey)
                .orElseThrow(() -> new RuntimeException("Failed to load public key"));
    }

    private Optional<RSAPrivateKey> getPrivateKey(String filename) {
        try {
            String key = new String(Files.readAllBytes(Paths.get(filename)))
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] keyBytes = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return Optional.of((RSAPrivateKey) kf.generatePrivate(spec));
        } catch (Exception e) {
            logger.error("Error loading private key: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<RSAPublicKey> getPublicKey(String filename) {
        try {
            String key = new String(Files.readAllBytes(Paths.get(filename)))
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");

            byte[] keyBytes = Base64.getDecoder().decode(key);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return Optional.of((RSAPublicKey) kf.generatePublic(spec));
        } catch (Exception e) {
            logger.error("Error loading public key: {}", e.getMessage());
            return Optional.empty();
        }
    }

    // Generate JWT token using email
    public String generateTokenUsingEmail(String email) {
        return generateToken(email);
    }

    // Generate JWT token using mobile number
    public String generateTokenUsingMobile(String mobileNo) {
        return generateToken(mobileNo);
    }

    private String generateToken(String subject) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    // Validate token
    public Boolean validateToken(String token, String subject) {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(subject)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return !isTokenExpired(decodedJWT);
        } catch (Exception exception) {
            logger.error("Invalid token: {}", exception.getMessage());
            return false;
        }
    }

    // Check if token is expired
    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }

    // Extract subject from token
    public String extractSubject(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }
    
    private <T> Optional<T> loadKey(String filename, KeyLoader<T> loader) {
        try {
            return loader.load(filename);
        } catch (Exception e) {
            logger.error("Error loading key from {}: {}", filename, e.getMessage());
            return Optional.empty();
        }
    }

    @FunctionalInterface
    private interface KeyLoader<T> {
        Optional<T> load(String filename) throws Exception;
    }
 
    
    
    
    
}
