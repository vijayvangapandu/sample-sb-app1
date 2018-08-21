package app.web.repository.match;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.web.repository.BaseRepository;
import app.web.repository.model.BaseMessage;
import app.web.repository.model.UserMatch;
import app.web.service.UserMatchesRequest;

@Service
public class MatchRepositoryImpl extends BaseRepository implements BaseMatchRepository {

	private Query buildQueryWithCriteria(UserMatchesRequest request) {
		Criteria criteria = buildUsersMatchesSearchCriteria(request);
		Query query = new Query(criteria);
		if (request.getCreatedSince() != null) {
			Sort sort = new Sort(Direction.DESC, BaseMessage.CREATED_TIMESTAMP);
			query = query.with(sort);
		}
		return query;
	}

	private Criteria buildUsersMatchesSearchCriteria(UserMatchesRequest request) {
		Criteria criteria = Criteria.where(UserMatch.USER_ID).is(request.getUserId());

		if (request.getCreatedSince() != null) {
			Criteria timeStampCriteria = Criteria.where(UserMatch.CREATED_TIMESTAMP).gt(request.getCreatedSince());
			criteria = new Criteria().andOperator(criteria, timeStampCriteria);
		}
		return criteria;
	}
	
	private Query buildGetMatchQueryWithCriteria(String userId, String matchId) {
		Criteria criteria = buildUsersMatchCriteria(userId, matchId);
		Query query = new Query(criteria);
		return query;
	}
	
	private Criteria buildUsersMatchCriteria(String userId, String matchId) {
		Criteria criteria = Criteria.where(UserMatch.USER_ID).is(userId);
		criteria = criteria.andOperator(Criteria.where(UserMatch.ID).is(matchId));
		return criteria;
	}

	@Override
	public List<UserMatch> getUserMatches(UserMatchesRequest request) {
		Query query = buildQueryWithCriteria(request);
		return getMongoOperations().find(query, UserMatch.class);
	}

	@Override
	public UserMatch getUserMatch(String userId, String matchId) {
		Query query = buildGetMatchQueryWithCriteria(userId, matchId);
		return getMongoOperations().findOne(query, UserMatch.class);
	}
	
}
