package app.web.dao.repository.message;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.WriteResult;

import app.web.dao.model.BaseMessage;
import app.web.dao.repository.BaseRepository;

@Service
public class MessagesRepositoryImpl extends BaseRepository implements BaseMessagesRepository {

	private static final int TOP_RECORD = 1;
	
	Logger log = LoggerFactory.getLogger(MessagesRepositoryImpl.class);

	@Override
	@Timed
	public BaseMessage getLastExchangedMessage(long matchId) {
		log.debug("Getting lastExchangedMessage for matchId {}", matchId);
		Query query = new Query(Criteria.where(BaseMessage.MATCHID).is(matchId));
		query = query.limit(TOP_RECORD);
		query = query.with(new Sort(Sort.Direction.DESC, BaseMessage.CREATED_TIMESTAMP));
		return getMongoOperations().findOne(query, BaseMessage.class);
	}
	
	@Override
	public int resetReadTimeStampForUserMatchAndMessageIds(long userId, long matchId, List<String> messageIds) {
		Criteria criteria = new Criteria().andOperator(Criteria.where(BaseMessage.MATCHID).is(matchId),Criteria.where(BaseMessage.RECIPIENT_USER_ID).is(userId));
		
		if(!CollectionUtils.isEmpty(messageIds)){
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.MESSAGE_ID).in(messageIds));
		}
		else{
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.READ_TIMESTAMP).ne(null));
		}
		
		Update update = new Update().set(BaseMessage.READ_TIMESTAMP, null);
		
		WriteResult result = getMongoOperations().updateMulti(new Query(criteria), update, BaseMessage.class);
		
		return result.getN();
		
	}
	
	@Override
	public int setReadTimeStampForUserMatchAndMessageIds(long userId, long matchId, List<String> messageIds, LocalDateTime readTimeStamp){
		
		Criteria criteria = new Criteria().andOperator(Criteria.where(BaseMessage.MATCHID).is(matchId),Criteria.where(BaseMessage.RECIPIENT_USER_ID).is(userId));
		
		if(!CollectionUtils.isEmpty(messageIds)){
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.MESSAGE_ID).in(messageIds));
		}
		else{
			criteria = new Criteria().andOperator(criteria, Criteria.where(BaseMessage.READ_TIMESTAMP).is(null));
		}
		
		Update update = new Update().set(BaseMessage.READ_TIMESTAMP, readTimeStamp);
		
		WriteResult result = getMongoOperations().updateMulti(new Query(criteria), update, BaseMessage.class);
		
		return result.getN();
	}

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

	@Timed(name = "update-messages-by-sender-id")
	@Override
	public int updateMessageTextBySenderUserId(long userId, String messageText) {
		Criteria critertiaToGetMessageBySenderId = Criteria.where(BaseMessage.SENDER_USER_ID).is(userId);

		Update updateMessageText = new Update().set(BaseMessage.VALUE, messageText);

		WriteResult result = getMongoOperations().updateMulti(new Query(critertiaToGetMessageBySenderId), updateMessageText,
				BaseMessage.class);

		return result.getN();
	}
	@Timed(name = "update-messages-by-receiver-id")
	@Override
	public int updateMessageTextByRecipientUserId(long userId, String messageText) {
		Criteria critertiaToGetMessageByReceiverId = Criteria.
				where(BaseMessage.RECIPIENT_USER_ID).is(userId);

		Update updateMessageText = new Update().set(BaseMessage.VALUE, messageText);

		WriteResult result = getMongoOperations().updateMulti(new Query(critertiaToGetMessageByReceiverId), updateMessageText,
				BaseMessage.class);

		return result.getN();
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

	/*@Override
	public List<BaseMessage> getPaginatedMessagesForUserMatch(GetMessagesRequestContext requestContext) {
		
		Criteria criteria = buildUserMatchMessagesCriteria(requestContext.getUserId(), requestContext.getMatchId(), requestContext.getMessageType());
		
		Criteria timeStampCriteria = null;
		
		Sort sort = new Sort(Direction.DESC, BaseMessage.CREATED_TIMESTAMP);
		
		if(requestContext.getCreatedSince() != null) {
			if (requestContext.getCount() == null || requestContext.getCount() > 0) {
				timeStampCriteria = Criteria.where(BaseMessage.CREATED_TIMESTAMP).gt(requestContext.getCreatedSince());
				sort = new Sort(Direction.ASC, BaseMessage.CREATED_TIMESTAMP);
			}
			else{
				timeStampCriteria = Criteria.where(BaseMessage.CREATED_TIMESTAMP).lt(requestContext.getCreatedSince());
			}
		}

		if (timeStampCriteria != null) {
			criteria = new Criteria().andOperator(criteria, timeStampCriteria);
		}
		
		Query query = new Query(criteria);
		
		if (requestContext.getPageNum() != null && requestContext.getCount() != null) {
			query = query.skip((requestContext.getPageNum() - 1) * requestContext.getCount()).limit(requestContext.getCount());
		}
		else if (requestContext.getCount() != null) {
			query = query.limit(Math.abs(requestContext.getCount()));
		}

		query = query.with(sort);
		return getMongoOperations().find(query, BaseMessage.class);
		
	}
*/
}

