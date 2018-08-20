package app.web.repository.user;

import java.util.List;

import app.web.repository.model.User;
import app.web.service.UserDataRequest;

public interface BaseUserRepository {

	public List<User> getUsers(UserDataRequest request);
	
}
