package app.web.rest;

import java.util.List;

import javax.ws.rs.container.AsyncResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.dto.User;
import app.web.rx.ExecutorServiceProvider;
import app.web.service.UserDataRequest;
import app.web.service.UserService;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

@Component
public class UserResourceRequestHandler {

	@Autowired
	private UserService userService;
	
	@Autowired ExecutorServiceProvider executorServiceProvider;
	
	public void handleGetUserRequest(UserDataRequest request, final AsyncResponse response) {
		
		Observable<UserDataRequest> userRequestObj = Observable.just(request);
		
		userRequestObj = userRequestObj.zipWith(userService.getUsers(request), populateUserInfo);
		userRequestObj.observeOn(Schedulers.from(executorServiceProvider.getTaskExecutor()))
				.subscribeOn(Schedulers.from(executorServiceProvider.getTaskExecutor())).subscribe(a -> {
					response.resume(a.getUsers());
				});
	}
	
	public Func2<UserDataRequest, List<User>, UserDataRequest> populateUserInfo = (userDataRequest, users) -> {
		userDataRequest.setUsers(users);
		return userDataRequest;
	};
	
	
}
