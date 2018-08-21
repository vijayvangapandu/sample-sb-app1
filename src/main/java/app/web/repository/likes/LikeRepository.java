package app.web.repository.likes;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.repository.model.UserInterest;

@Repository
public interface LikeRepository extends MongoRepository<UserInterest, String>, BaseLikeRepository {

}
