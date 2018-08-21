package app.web.rest;

import java.util.List;

import javax.ws.rs.container.AsyncResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.dto.UserDto;
import app.web.repository.model.User;
import app.web.rx.ExecutorServiceProvider;
import app.web.service.UserDataRequest;
import app.web.service.UserService;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

@Component
public class UserResourceRequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserResourceRequestHandler.class);

	@Autowired
	private UserService userService;

	@Autowired
	ExecutorServiceProvider executorServiceProvider;

	public void handleGetUserRequest(UserDataRequest request, final AsyncResponse response) {

		Observable<UserDataRequest> userRequestObj = Observable.just(request);

		userRequestObj = userRequestObj.zipWith(userService.getUsers(request), populateUserInfo);
		userRequestObj.observeOn(Schedulers.from(executorServiceProvider.getTaskExecutor()))
				.subscribeOn(Schedulers.from(executorServiceProvider.getTaskExecutor())).subscribe(a -> {
					logger.info("Total users retrived for request {} is {}", request, a.getUsers().size());
					response.resume(a.getUsers());
				});
	}

	public Func2<UserDataRequest, List<UserDto>, UserDataRequest> populateUserInfo = (userDataRequest, users) -> {
		userDataRequest.setUsers(users);
		return userDataRequest;
	};

	public UserDto getUserDto(String userId) {
		return userService.getUserDto(userId);
	}

	public User getUser(String userId) {
		return userService.getUser(userId);
	}

	public User saveUser(User user) {
		return userService.saveUser(user);
	}

}
