package pl.zti.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.zti.atlas.security.SecretKeyGenerator;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class AtlasOfFishesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlasOfFishesApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}

	@EventListener(ApplicationReadyEvent.class)
	public void afterStartupActions() throws NoSuchAlgorithmException {
		new SecretKeyGenerator().generateSecretKey();
	}
}
