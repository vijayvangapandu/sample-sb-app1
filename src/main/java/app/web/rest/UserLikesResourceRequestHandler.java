package app.web.rest;

import java.util.List;

import javax.ws.rs.container.AsyncResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.repository.model.UserInterest;
import app.web.rx.ExecutorServiceProvider;
import app.web.service.UserLikesRequest;
import app.web.service.UserLikesService;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

@Component
public class UserLikesResourceRequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserLikesResourceRequestHandler.class);

	@Autowired
	private UserLikesService userLikesService;

	@Autowired
	ExecutorServiceProvider executorServiceProvider;

	public void handleGetUserLikesRequest(UserLikesRequest request, final AsyncResponse response) {

		Observable<UserLikesRequest> userMatchesRequestObj = Observable.just(request);

		userMatchesRequestObj = userMatchesRequestObj.zipWith(userLikesService.getUserLikes(request), populateUserLikesInfo);
		userMatchesRequestObj.observeOn(Schedulers.from(executorServiceProvider.getTaskExecutor()))
				.subscribeOn(Schedulers.from(executorServiceProvider.getTaskExecutor())).subscribe(a -> {
					logger.info("Total likes retrived for user {} is {}", request.getUserId(), a.getUserLikes().size());
					response.resume(a.getUserLikes());
				});
	}

	public Func2<UserLikesRequest, List<UserInterest>, UserLikesRequest> populateUserLikesInfo = (userLikesRequest, userLikes) -> {
		userLikesRequest.setUserLikes(userLikes);
		return userLikesRequest;
	};

	public UserInterest getUserInterest(String userId, String otherUserId) {
		return userLikesService.getUserLike(userId, otherUserId);
	}

	public UserInterest swipeLeft(String declinedUser, String otherUser) {
		return userLikesService.swipeLeft(declinedUser, otherUser);
	}
	
	public UserInterest swipeRight(String swipedUser, String otherUserId) {
		return userLikesService.swipeRight(swipedUser, otherUserId);
	}

}
