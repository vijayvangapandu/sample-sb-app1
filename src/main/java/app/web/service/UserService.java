package app.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.dao.UserDao;
import app.web.dao.model.User;
import rx.Observable;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserService() {

	}

	public Observable<List<User>> getUsers(UserDataRequest request) {
		return Observable.defer(() -> Observable.just(userDao.getUsers(request)));

	}

	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	public User getUser(String userId) {

		return userDao.getUser(userId);
	}

}
