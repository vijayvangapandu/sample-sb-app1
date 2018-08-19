package app.web.service;

import java.util.List;
import java.util.Optional;

import app.web.dto.User;

public class UserDataRequest {

	private Optional<Integer> pageNumber;
	private Optional<Integer> pageSize;
	private Optional<Long> dateSince;
	private Optional<Long> userId;
	private List<User> users;
	
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
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
