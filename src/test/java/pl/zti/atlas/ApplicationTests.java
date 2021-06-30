package pl.zti.atlas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import pl.zti.atlas.controller.AdminFishController;
import pl.zti.atlas.controller.FishController;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.service.FishService;

import java.util.List;

@SpringBootTest
class ApplicationTests {

	private final FishService fishService;

	private final FishController fishController;
	private final AdminFishController adminFishController;

	@Autowired
	ApplicationTests(FishService fishService, FishController fishController, AdminFishController adminFishController) {
		this.fishService = fishService;
		this.fishController = fishController;
		this.adminFishController = adminFishController;
	}

	Fish getFish1() {
		return new Fish("id1", "Amur Bialy", "Karpiowate", "stawy, rzeka, ujście, jezioro",
			"Brak ", "Brak ", "description", "imageUrl");
	}

	Fish getFish2() {
		return new Fish("id2", "Brzana", "Karpiowate", "strumień, rzeka", "do 40 cm",
				"od 1 stycznia do 30 czerwca", "description", "imageUrl");
	}

	@BeforeEach
	void addFishes() {
		fishService.save(getFish1());
		fishService.save(getFish2());
	}

	@AfterEach
	void removeFishes() {
		fishService.deleteById("id1");
		fishService.deleteById("id2");
	}

	@Test
	void getById() {
		ResponseEntity<Fish> response = fishController.getFishById("id1");
		Fish fish = response.getBody();

		Assertions.assertEquals(fish, getFish1());
	}

	@Test
	void searchFishes() {
		ResponseEntity<List<Fish>> response = fishController.findFishes("Bialy", "Karpiowate", "");
		List<Fish> fishes = response.getBody();

		Assertions.assertTrue(fishes.contains(getFish1()));
		Assertions.assertFalse(fishes.contains(getFish2()));
	}

	@Test
	void findAll() {
		ResponseEntity<List<Fish>> response = fishController.getAll();
		List<Fish> fishes = response.getBody();

		Assertions.assertTrue(fishes.contains(getFish1()));
		Assertions.assertTrue(fishes.contains(getFish2()));
	}

	@Test
	void deleteFish() {
		adminFishController.deleteFish("id1");
		ResponseEntity<List<Fish>> response = fishController.getAll();
		List<Fish> fishes = response.getBody();

		Assertions.assertFalse(fishes.contains(getFish1()));
		Assertions.assertTrue(fishes.contains(getFish2()));
	}

	@Test
	void updateFish() {
		Fish fish = getFish1();
		fish.setName("Inny Amur Bialy");
		adminFishController.updateFish(fish.getId(), fish);

		ResponseEntity<Fish> fishResponse = fishController.getFishById("id1");
		Fish newFish = fishResponse.getBody();
		Assertions.assertNotEquals(newFish, getFish1());
	}

	@Test
	void addFish() {
		Fish fish = getFish1();
		fish.setId("id3");
		fish.setName("Inna ryba");
		adminFishController.addFish(fish);

		ResponseEntity<Fish> response = fishController.getFishById("id3");
		Fish storedFish = response.getBody();
		Assertions.assertEquals(fish, storedFish);
	}

}
