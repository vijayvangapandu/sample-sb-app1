package app.web.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import app.web.repository.model.UserMatch;

public class UserMatchesRequest {

	private String userId;
	private Optional<Integer> pageNumber;
	private Optional<Integer> pageSize;
	private LocalDateTime createdSince;
	private List<UserMatch> userMatches;
	private Optional<Integer> matchState;
	
	public Optional<Integer> getMatchState() {
		return matchState;
	}
	public void setMatchState(Optional<Integer> matchState) {
		this.matchState = matchState;
	}
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
	
	public void setPageSize(Optional<Integer> pageSize) {
		this.pageSize = pageSize;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getCreatedSince() {
		return createdSince;
	}
	public void setCreatedSince(LocalDateTime createdSince) {
		this.createdSince = createdSince;
	}
	public void setPageNumber(Optional<Integer> pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<UserMatch> getUserMatches() {
		return userMatches;
	}
	public void setUserMatches(List<UserMatch> userMatches) {
		this.userMatches = userMatches;
	}
	
}
