package app.web.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import app.web.dto.UserDto;
import app.web.repository.model.User;

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
		int age = 0;
		if(user.getUserBirthDate() != null) {
			age = Period.between(user.getUserBirthDate().toLocalDate(), LocalDate.now()).getYears();
		}
		return age;
	}

}
