
package app.web.repository.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.repository.model.BaseMessage;

@Repository
public interface MessagesRepository extends MongoRepository<BaseMessage<?>, String>, BaseMessagesRepository  {
	
}
