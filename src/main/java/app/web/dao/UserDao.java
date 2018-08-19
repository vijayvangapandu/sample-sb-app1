package app.web.dao;

import java.util.List;

import app.web.dto.User;
import app.web.service.UserDataRequest;

public interface UserDao {

	public List<User> getUsers(UserDataRequest request);
}
