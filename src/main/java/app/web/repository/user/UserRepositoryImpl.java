package app.web.repository.user;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.web.repository.BaseRepository;
import app.web.repository.model.BaseMessage;
import app.web.repository.model.User;
import app.web.service.UserDataRequest;

@Service
public class UserRepositoryImpl extends BaseRepository implements BaseUserRepository {

	@Override
	public List<User> getUsers(UserDataRequest request) {

		Query query = buildQueryWithCriteria(request);
		return getMongoOperations().find(query, User.class);
	}

	private Query buildQueryWithCriteria(UserDataRequest request) {
		Criteria criteria = buildUsersSearchCriteria(request);
		Query query = new Query(criteria);
		if (request.getDateSince().isPresent()) {
			Sort sort = new Sort(Direction.DESC, BaseMessage.CREATED_TIMESTAMP);
			query = query.with(sort);
		}
		return query;
	}

	private Criteria buildUsersSearchCriteria(UserDataRequest request) {
		Criteria criteria = null;

		if (request.getGender().isPresent()) {
			criteria = Criteria.where(User.GENDER).is(request.getGender().get());
		}

		if (request.getDateSince().isPresent()) {
			if(criteria != null) {
				Criteria timeStampCriteria = Criteria.where(User.CREATED_TIMESTAMP).gt(request.getCreatedSince());
				criteria = new Criteria().andOperator(criteria, timeStampCriteria);
			} else {
				criteria = Criteria.where(User.CREATED_TIMESTAMP).gt(request.getCreatedSince());
			}
			
		} 
		if(criteria == null) {
			criteria = new Criteria();
		}

		return criteria;
	}

}
