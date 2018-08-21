package app.web.repository.match;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.repository.model.UserMatch;

@Repository
public interface MatchRepository extends MongoRepository<UserMatch, String>, BaseMatchRepository {

}
