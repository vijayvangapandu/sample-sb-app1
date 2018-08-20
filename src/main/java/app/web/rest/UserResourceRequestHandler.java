package app.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.container.AsyncResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.dto.UserDto;
import app.web.repository.model.User;
import app.web.rx.ExecutorServiceProvider;
import app.web.service.UserDataRequest;
import app.web.service.UserService;
import app.web.service.UserTransformer;
import rx.Observable;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

@Component
public class UserResourceRequestHandler {

	@Autowired
	private UserService userService;
	
	@Autowired ExecutorServiceProvider executorServiceProvider;
	
	@Autowired
	private UserTransformer userTransformer;
	
	public void handleGetUserRequest(UserDataRequest request, final AsyncResponse response) {
		
		Observable<UserDataRequest> userRequestObj = Observable.just(request);
		
		userRequestObj = userRequestObj.zipWith(userService.getUsers(request), populateUserInfo);
		userRequestObj.observeOn(Schedulers.from(executorServiceProvider.getTaskExecutor()))
				.subscribeOn(Schedulers.from(executorServiceProvider.getTaskExecutor())).subscribe(a -> {
					List<User> users = a.getUsers();
					List<UserDto> userDtoList = new ArrayList<>();
					if(CollectionUtils.isNotEmpty(users)) {
						userDtoList = users.stream().map(u -> 
							userTransformer.buildUserDto(u)
						).collect(Collectors.toList());
						
					}
					response.resume(userDtoList);
				});
	}
	
	public Func2<UserDataRequest, List<User>, UserDataRequest> populateUserInfo = (userDataRequest, users) -> {
		userDataRequest.setUsers(users);
		return userDataRequest;
	};
	
	public UserDto getUserDto(String userId) {
		return  userService.getUserDto(userId);
	}
	
	public User getUser(String userId) {
		return userService.getUser(userId);
	}
	
	public User saveUser(User user) {
		return userService.saveUser(user);
	}
	
	
}
