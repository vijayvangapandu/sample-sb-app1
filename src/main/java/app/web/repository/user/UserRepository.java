package app.web.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.repository.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, BaseUserRepository {

}
