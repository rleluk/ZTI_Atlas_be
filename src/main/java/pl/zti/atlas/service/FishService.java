package pl.zti.atlas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zti.atlas.exception.NotFoundException;
import pl.zti.atlas.model.Fish;
import pl.zti.atlas.repository.FishRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FishService {

    private final FishRepository fishRepository;

    @Autowired
    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public List<Fish> findAll() {
        return fishRepository.findAll();
    }

    public List<Fish> findFishes(String name, String species, String waterType) {
        return fishRepository.findAllByNameContainsAndSpeciesContainsAndWaterTypeContains(name, species, waterType);
    }

    public Fish save(Fish fish) {
        return fishRepository.save(new Fish(
            fish.getId(), fish.getName(), fish.getSpecies(), fish.getWaterType(),
            fish.getProtectionPeriod(), fish.getProtectionLength(), fish.getDescription(), fish.getImageUrl()
        ));
    }

    public Optional<Fish> findById(String id) {
        return fishRepository.findById(id);
    }

    public Fish updateFish(Fish fish) {
        Optional<Fish> fishData = this.findById(fish.getId());

        if (fishData.isPresent()) {
            Fish oldFish = fishData.get();
            oldFish.setName(fish.getName());
            oldFish.setSpecies(fish.getSpecies());
            oldFish.setWaterType(fish.getWaterType());
            oldFish.setProtectionPeriod(fish.getProtectionPeriod());
            oldFish.setProtectionLength(fish.getProtectionLength());
            oldFish.setImageUrl(fish.getImageUrl());
            oldFish.setDescription(fish.getDescription());
            return fishRepository.save(oldFish);
        } else {
            throw new NotFoundException();
        }
    }

    public void deleteById(String id) {
        fishRepository.deleteById(id);
    }
}
