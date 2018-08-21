package app.web.repository.message;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.Timed;

import app.web.repository.BaseRepository;
import app.web.repository.model.BaseMessage;

@Service
public class MessagesRepositoryImpl extends BaseRepository implements BaseMessagesRepository {

	private static final int TOP_RECORD = 1;
	
	private static final Logger log = LoggerFactory.getLogger(MessagesRepositoryImpl.class);

	@Override
	@Timed
	public BaseMessage<?> getLastExchangedMessage(long matchId) {
		log.debug("Getting lastExchangedMessage for matchId {}", matchId);
		Query query = new Query(Criteria.where(BaseMessage.MATCHID).is(matchId));
		query = query.limit(TOP_RECORD);
		query = query.with(new Sort(Sort.Direction.DESC, BaseMessage.CREATED_TIMESTAMP));
		return getMongoOperations().findOne(query, BaseMessage.class);
	}
	

	@SuppressWarnings("rawtypes")
	@Override
	public List<BaseMessage> getMessagesForUserAndMatch(long userId, long matchId, int messageType) {
		
		Criteria criteria = buildUserMatchMessagesCriteria(userId, matchId, messageType);
		
		Query query = new Query(criteria).with(new Sort(Direction.DESC, BaseMessage.CREATED_TIMESTAMP));
		
		return getMongoOperations().find(query, BaseMessage.class);
		
	}

	@Override
	public Long getUnreadMessagesCountForUserAndMatch(long userId, long matchId) {
		Criteria criteria =  new Criteria().andOperator(where(BaseMessage.READ_TIMESTAMP).is(null), where(BaseMessage.RECIPIENT_USER_ID).is(userId), where(BaseMessage.MATCHID).is(matchId));
		return getMongoOperations().count(new Query(criteria), BaseMessage.class);
	}

	private Criteria buildUserMatchMessagesCriteria(long userId, long matchId, Integer messageType) {
		Criteria criteria = new Criteria().orOperator(Criteria.where(BaseMessage.SENDER_USER_ID).is(userId),Criteria.where(BaseMessage.RECIPIENT_USER_ID).is(userId));
		
		if (messageType != null) {
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.MATCHID).is(matchId),
			        Criteria.where(BaseMessage.TYPE).is(messageType));
		} else {
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.MATCHID).is(matchId));
		}
		
		return criteria;
	}

}

