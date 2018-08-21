package app.web.rest;

import java.util.List;

import javax.ws.rs.container.AsyncResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.repository.model.UserMatch;
import app.web.rx.ExecutorServiceProvider;
import app.web.service.UserMatchesRequest;
import app.web.service.UserMatchesService;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

@Component
public class UserMatchesResourceRequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserMatchesResourceRequestHandler.class);

	@Autowired
	private UserMatchesService userMatchesService;

	@Autowired
	ExecutorServiceProvider executorServiceProvider;

	public void handleGetUserMatchesRequest(UserMatchesRequest request, final AsyncResponse response) {

		Observable<UserMatchesRequest> userMatchesRequestObj = Observable.just(request);

		userMatchesRequestObj = userMatchesRequestObj.zipWith(userMatchesService.getUserMatches(request), populateUserMatchesInfo);
		userMatchesRequestObj.observeOn(Schedulers.from(executorServiceProvider.getTaskExecutor()))
				.subscribeOn(Schedulers.from(executorServiceProvider.getTaskExecutor())).subscribe(a -> {
					logger.info("Total matches retrived for user {} is {}", request.getUserId(), a.getUserMatches().size());
					response.resume(a.getUserMatches());
				});
	}

	public Func2<UserMatchesRequest, List<UserMatch>, UserMatchesRequest> populateUserMatchesInfo = (userMatchesRequest, userMatches) -> {
		userMatchesRequest.setUserMatches(userMatches);
		return userMatchesRequest;
	};

	public UserMatch getUserMatch(String userId, String matchId) {
		return userMatchesService.getUserMatch(userId, matchId);
	}

	public UserMatch saveUserMatch(UserMatch userMatch) {
		return userMatchesService.saveUserMatch(userMatch);
	}

}
