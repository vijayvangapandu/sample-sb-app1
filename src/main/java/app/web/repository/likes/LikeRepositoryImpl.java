package app.web.repository.likes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import app.web.repository.BaseRepository;
import app.web.repository.model.BaseMessage;
import app.web.repository.model.UserInterest;
import app.web.service.UserLikesRequest;

@Service
public class LikeRepositoryImpl extends BaseRepository implements BaseLikeRepository {

	private Query buildQueryWithCriteria(UserLikesRequest request) {
		Criteria criteria = buildUserLikesCriteria(request);
		Query query = new Query(criteria);
		if (request.getCreatedSince() != null) {
			Sort sort = new Sort(Direction.DESC, BaseMessage.CREATED_TIMESTAMP);
			query = query.with(sort);
		}
		return query;
	}

	private Criteria buildUserLikesCriteria(UserLikesRequest request) {
		Criteria criteria = null;
		if (request.getUserId().isPresent()) {
			criteria = Criteria.where(UserInterest.USER_ID).is(request.getUserId());
		}

		if (request.getOtherUserId().isPresent()) {
			if (criteria != null) {
				Criteria otherUserCriteria = Criteria.where(UserInterest.OTHER_USER_ID).is(request.getOtherUserId());
				criteria = new Criteria().andOperator(criteria, otherUserCriteria);
			} else {
				criteria = Criteria.where(UserInterest.OTHER_USER_ID).is(request.getOtherUserId());
			}
		}

		if (request.getLikeStatus().isPresent()) {
			Criteria likeStatusCriteria = Criteria.where(UserInterest.LIKE_STATUS).is(request.getLikeStatus().get());
			criteria = new Criteria().andOperator(criteria, likeStatusCriteria);
		}

		/*
		 * if (request.getCreatedSince() != null) { Criteria timeStampCriteria =
		 * Criteria.where(UserMatch.CREATED_TIMESTAMP).gt(request.getCreatedSince());
		 * criteria = new Criteria().andOperator(criteria, timeStampCriteria); }
		 */
		return criteria;
	}

	@Override
	public List<UserInterest> getUserLikes(UserLikesRequest request) {
		Query query = buildQueryWithCriteria(request);
		return getMongoOperations().find(query, UserInterest.class);
	}

}
