package basepackage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define path to your public key (adjust to your actual path)
    private static final String PUBLIC_KEY_PATH = "C:\\Users\\Manju\\MyProject\\MyPizza\\src\\main\\resources\\publicKey.pem";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf(csrf -> csrf.disable())  
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/user/login", "/api/users/create", "/api/pizza/get/*").permitAll()  // Public endpoints
                .anyRequest().authenticated()  // Require authentication for all other requests
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> {
					try {
						jwt.decoder(jwtDecoder());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				})  // Use custom JWT decoder
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
            );

        return http.build();
    }

    // Custom JwtDecoder to verify JWTs using the RSA public key
    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        String publicKeyPEM = new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH)))
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");  // Clean up the key string

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);  // Decode Base64 public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        // Cast to RSAPublicKey
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(spec);

        return NimbusJwtDecoder.withPublicKey(publicKey).build();  // Build JwtDecoder with the RSA public key
    }

    // Bean for password encoding (using BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Return BCryptPasswordEncoder bean
    }
}
