package app.web.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.web.dao.UserDao;
import app.web.dao.model.User;
import app.web.dao.repository.user.UserRepository;
import app.web.service.UserDataRequest;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsers(UserDataRequest request) {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(String id) {
		return userRepository.findOne(id);
	}

	@Override
	public void deleteUser(String id) {
		userRepository.delete(id);
	}
	
	


}
