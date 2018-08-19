package app.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.dao.UserDao;
import app.web.dto.User;
import rx.Observable;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public Observable<List<User>> getUsers(UserDataRequest request) {
		return Observable.defer(() -> Observable.just(userDao.getUsers(request)));

	}

}
