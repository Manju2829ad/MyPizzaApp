package basepackage.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;


@EnableCaching
@Configuration
public class CacheConfig {
	@Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("pizza", "pizzaId");
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES) // Cache entries expire after 30 minutes
            .maximumSize(1000));                    // Max 1000 entries
        return cacheManager;
    }
	
	
}
