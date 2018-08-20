package app.web.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {

	public static final String CREATED_TIMESTAMP = "cts";
	public static final String GENDER = "gender";
	public static final String BIRTH_DATE = "bdate";
	public static final String USER_NAME = "name";
	
	@Id
	private String userId;
	
	@Field(value = USER_NAME)
	private String name;
	
	@Field(value = GENDER)
	private int gender;
	
	@Field(value = BIRTH_DATE)
	private LocalDateTime userBirthDate;
	
	@Field(value = CREATED_TIMESTAMP)
	private LocalDateTime createdTimeStamp;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public LocalDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public LocalDateTime getUserBirthDate() {
		return userBirthDate;
	}
	public void setUserBirthDate(LocalDateTime userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
	public static String getCreatedTimestamp() {
		return CREATED_TIMESTAMP;
	}
	public static String getBirthDate() {
		return BIRTH_DATE;
	}
	public static String getUserName() {
		return USER_NAME;
	}
	
}
