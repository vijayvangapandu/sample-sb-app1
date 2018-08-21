package app.web.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.dto.UserDto;
import app.web.repository.model.User;
import app.web.repository.user.UserRepository;
import rx.Observable;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserTransformer userTransformer;

	public Observable<List<UserDto>> getUsers(UserDataRequest request) {
		return Observable.defer(() -> Observable.just(getUsersList(request)));

	}

	private List<UserDto> getUsersList(UserDataRequest request) {
		List<User> users = userRepository.getUsers(request);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.stream().map(u -> userTransformer.buildUserDto(u)).collect(Collectors.toList());
		}
		return Lists.emptyList();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User getUser(String userId) {
		return userRepository.findOne(userId);
	}

	public UserDto getUserDto(String userId) {

		User user = userRepository.findOne(userId);
		if(user != null) {
			return userTransformer.buildUserDto(user);
		}
		return null;

	}

}
