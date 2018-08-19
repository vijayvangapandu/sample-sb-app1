package app.web.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import app.web.dao.UserDao;
import app.web.dto.User;
import app.web.service.UserDataRequest;

@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public List<User> getUsers(UserDataRequest request) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<User> users = new ArrayList<>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setName("vijay");
		user1.setGender(1);
		Calendar today = Calendar.getInstance();
		today.add(Calendar.YEAR, 25);
		user1.setBirthDate(today.getTimeInMillis());
		users.add(user1);
		return users;
	}


}
