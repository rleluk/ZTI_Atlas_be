package pl.zti.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtlasOfFishesApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(AtlasOfFishesApplication.class, args);
	}

//	@Autowired
//	private UserRepository userRepository;

//	@Override
//	public void run(String... args) throws Exception {
//		userRepository.save(new User("1", "Jurek"));
//		userRepository.save(new User("2", "Grzegorz"));
//	}

}
