package app.web.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import app.web.dao.model.User;
import app.web.dto.UserDto;

@Service
public class UserTransformer {

	public void transformToUserDto(User user, UserDto userDto) {
		userDto.setAge(calculateAge(user));
		userDto.setName(user.getName());
		userDto.setGender(user.getGender());
		userDto.setUserId(user.getUserId());
	}
	
	public UserDto buildUserDto(User user) {
		UserDto userDto = new UserDto();
		transformToUserDto(user, userDto);
		return userDto;
	}

	private int calculateAge(User user) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(user.getBirthDate());
		LocalDate birthLocalDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		int age = Period.between(birthLocalDate, LocalDate.now()).getYears();

		return age;
	}

}
