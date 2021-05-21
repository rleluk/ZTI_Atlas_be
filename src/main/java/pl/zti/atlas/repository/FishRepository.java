package pl.zti.atlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.zti.atlas.model.Fish;

@Repository
public interface FishRepository extends MongoRepository<Fish, String> {

    public Fish findByName(String name);

}
