package basepackage.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    // 🔹 Use a secret key instead of RSA keys
    private static final String SECRET_KEY = "l4G5ZEErTqvOxXr3A+Ez8vZvl49/SwMCc8SI1f0xQEE=";

    @Value("${jwt.expiration.time:86400000}") // Default 1 day
    private long expirationTime;

    public String generateTokenUsingEmail(String email) {
        return generateToken(email);
    }

    public String generateTokenUsingMobile(String mobileNo) {
        return generateToken(mobileNo);
    }

    private String generateToken(String subject) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY); // 🔹 HMAC256 instead of RSA

        return JWT.create()
                .withSubject(subject)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm);
    }

    public Boolean validateToken(String token, String subject) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
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

    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public String extractSubject(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }
}
