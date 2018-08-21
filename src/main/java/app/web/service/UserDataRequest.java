package app.web.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import app.web.dto.UserDto;

public class UserDataRequest {

	private Optional<Integer> pageNumber;
	private Optional<Integer> pageSize;
	private Optional<Long> dateSince;
	private Optional<Long> userId;
	private Optional<Integer> gender;
	private Optional<Integer> age;
	
	private LocalDateTime createdSince;
	
	private List<UserDto> users;
	
	public Optional<Integer> getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = Optional.ofNullable(pageNumber);
	}
	public Optional<Integer> getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = Optional.ofNullable(pageSize);
	}
	public Optional<Long> getDateSince() {
		return dateSince;
	}
	public void setDateSince(Long dateSince) {
		this.dateSince = Optional.ofNullable(dateSince);
	}
	
	public Optional<Long> getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = Optional.ofNullable(userId);
	}
	
	public void setUserId(Optional<Long> userId) {
		this.userId = userId;
	}
	
	public void setPageSize(Optional<Integer> pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setDateSince(Optional<Long> dateSince) {
		this.dateSince = dateSince;
	}
	
	public void setPageNumber(Optional<Integer> pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public List<UserDto> getUsers() {
		return users;
	}
	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
	public Optional<Integer> getGender() {
		return gender;
	}
	public void setGender(Optional<Integer> gender) {
		this.gender = gender;
	}
	public Optional<Integer> getAge() {
		return age;
	}
	public void setAge(Optional<Integer> age) {
		this.age = age;
	}
	public LocalDateTime getCreatedSince() {
		return createdSince;
	}
	public void setCreatedSince(LocalDateTime createdSince) {
		this.createdSince = createdSince;
	}
	
	public void setGender(Integer gender) {
		this.gender = Optional.ofNullable(gender);
	}
	
}
