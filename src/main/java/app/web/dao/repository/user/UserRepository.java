package app.web.dao.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.dao.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, BaseUserRepository {

}
