package app.web.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.dto.UserDto;
import app.web.external.client.UserServiceClient;
import app.web.repository.model.User;
import app.web.repository.user.UserRepository;
import rx.Observable;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserServiceClient client;

	public UserService() {

	}

	public Observable<List<User>> getUsers(UserDataRequest request) {
		return Observable.defer(() -> Observable.just(userRepository.getUsers(request)));

	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}

	public UserDto getUserDto(String userId) {

		List<UserDto> users = client.getUsers();
		if(CollectionUtils.isNotEmpty(users)) {
			for(UserDto user: users) {
				if(user.getUserId().equals(userId)) {
					return user;
				}
			}
		}
		return null;
	}

}
