package pl.zti.atlas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtlasOfFishesApplication /*implements CommandLineRunner*/ {

//	@Autowired
//	private FishRepository fishRepository;

	public static void main(String[] args) {
		SpringApplication.run(AtlasOfFishesApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		fishRepository.save(new Fish("1", "Okoń"));
//		fishRepository.save(new Fish("2", "Szczupak"));
//
//		System.out.println("Fish found with findAll():");
//		System.out.println("-------------------------------");
//		for (Fish fish : fishRepository.findAll()) {
//			System.out.println(fish);
//		}
//		System.out.println();
//	}

}
