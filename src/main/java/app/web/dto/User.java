package app.web.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class User {

	private String name;
	private long userId;
	private int gender;
	transient private long birthDate;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public void setBirthDate(long birthDate) {
		this.birthDate = birthDate;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(birthDate);
		LocalDate birthLocalDate = LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
		age = Period.between(LocalDate.now(), birthLocalDate).getYears();
	}
	public int getAge() {
		return age;
	}
	
	
}
