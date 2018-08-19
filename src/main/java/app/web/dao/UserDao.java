package app.web.dao;

import java.util.List;

import app.web.dao.model.User;
import app.web.service.UserDataRequest;

public interface UserDao {

	public List<User> getUsers(UserDataRequest request);
	public User saveUser(User user);
	public User getUser(String id);
	public void deleteUser(String id);
	
}
