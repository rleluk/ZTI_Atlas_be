package pl.zti.atlas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.zti.atlas.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {}
