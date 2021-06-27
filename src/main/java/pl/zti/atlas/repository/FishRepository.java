package pl.zti.atlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.zti.atlas.model.Fish;

import java.util.List;

@Repository
public interface FishRepository extends MongoRepository<Fish, String> {

    List<Fish> findAllByNameContainsIgnoreCaseAndSpeciesContainsIgnoreCaseAndWaterTypeContainsIgnoreCase(
        String name, String species, String waterType);

}
