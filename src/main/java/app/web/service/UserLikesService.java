package app.web.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.repository.likes.LikeRepository;
import app.web.repository.model.UserInterest;
import app.web.repository.model.UserMatch;
import app.web.repository.model.enums.LikeStatus;
import app.web.repository.model.enums.MatchState;
import rx.Observable;

@Service
public class UserLikesService {

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private UserMatchesService useMatchesService;

	public Observable<List<UserInterest>> getUserLikes(UserLikesRequest request) {
		return Observable.defer(() -> Observable.just(getUserLikesList(request)));

	}

	private List<UserInterest> getUserLikesList(UserLikesRequest request) {
		List<UserInterest> interests = likeRepository.getUserLikes(request);
		if (CollectionUtils.isNotEmpty(interests)) {
			return interests;
		}
		return Lists.emptyList();
	}

	public UserInterest getUserLike(String userId, String otherUserId) {
		UserLikesRequest request = new UserLikesRequest();
		request.setUserId(Optional.ofNullable(userId));
		request.setOtherUserId(Optional.ofNullable(otherUserId));
		List<UserInterest> interests = getUserLikesList(request);
		if (CollectionUtils.isNotEmpty(interests)) {
			return interests.iterator().next();
		}
		return null;
	}

	public UserInterest createOneWayInterest(String userId, String otherUserId) {
		UserInterest userInterest = new UserInterest();
		userInterest.setUserId(userId);
		userInterest.setOtherUserId(otherUserId);
		userInterest.setSwipeDate(LocalDateTime.now());
		userInterest.setStatus(LikeStatus.LIKE_INITIATED);
		return likeRepository.save(userInterest);
	}

	public UserInterest declineInterest(String declinedUser, String initiatedUser) {
		UserInterest interest = getUserLike(initiatedUser, declinedUser);
		if (interest != null) {
			interest.setStatus(LikeStatus.LIKE_DECLINED);
			interest.setDeclinedDate(LocalDateTime.now());
			return likeRepository.save(interest);
		}
		return null;
	}

	public UserInterest swipeLeft(String declinedUser, String otherUser) {
		UserInterest interest = getUserLike(otherUser, declinedUser);
		if (interest != null) {
			if (interest.getStatus() != LikeStatus.LIKE_DECLINED) {
				interest.setSwipeDate(LocalDateTime.now());
				interest.setStatus(LikeStatus.LIKE_DECLINED);
				interest =  likeRepository.save(interest);
			}
		} else {
			interest = new UserInterest();
			interest.setUserId(declinedUser);
			interest.setOtherUserId(otherUser);
			interest.setSwipeDate(LocalDateTime.now());
			interest.setStatus(LikeStatus.LIKE_DECLINED);
			interest =  likeRepository.save(interest);
		}
		return interest;

	}

	public UserInterest swipeRight(String swipedUser, String otherUser) {
		UserInterest interest = getUserLike(otherUser, swipedUser);
		if (interest != null && interest.getStatus() == LikeStatus.LIKE_INITIATED) {
			interest = mutualLike(interest);
		} else {
			interest = createOneWayInterest(swipedUser, otherUser);
		}

		return interest;
	}

	public UserInterest mutualLike(UserInterest userInterest) {
		userInterest.setStatus(LikeStatus.MUTUAL_LIKE);
		userInterest.setMutualLikeDate(LocalDateTime.now());
		userInterest = likeRepository.save(userInterest);
		useMatchesService.saveUserMatch(buildUserMatch(userInterest.getUserId(), userInterest.getOtherUserId()));
		return userInterest;
	}

	/*
	 * public UserInterest mutualLike(String initiatedUser, String otherUser) {
	 * UserInterest interest = getUserLike(initiatedUser, otherUser); if(interest !=
	 * null && interest.getStatus() == LikeStatus.LIKE_INITIATED) { interest =
	 * mutualLike(interest); } return interest; }
	 */

	private UserMatch buildUserMatch(String userId, String otherUserId) {
		UserMatch userMatch = new UserMatch();
		userMatch.setUserId(userId);
		userMatch.setMatchedUserId(otherUserId);
		userMatch.setMatchedDate(LocalDateTime.now());
		userMatch.setMatchState(MatchState.NEW);
		return userMatch;
	}

}
