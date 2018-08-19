
package app.web.dao.repository.message;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.web.dao.model.BaseMessage;

@Repository
public interface MessagesRepository extends MongoRepository<BaseMessage, String>, BaseMessagesRepository  {
	
}
