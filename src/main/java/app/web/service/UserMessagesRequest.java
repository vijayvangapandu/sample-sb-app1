package app.web.service;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserMessagesRequest {

	private String userId;
	private Optional<String> matchId;
	private Optional<Integer> pageNumber;
	private Optional<Integer> pageSize;
	private LocalDateTime createdSince;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Optional<String> getMatchId() {
		return matchId;
	}
	public void setMatchId(Optional<String> matchId) {
		this.matchId = matchId;
	}
	public Optional<Integer> getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Optional<Integer> pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Optional<Integer> getPageSize() {
		return pageSize;
	}
	public void setPageSize(Optional<Integer> pageSize) {
		this.pageSize = pageSize;
	}
	public LocalDateTime getCreatedSince() {
		return createdSince;
	}
	public void setCreatedSince(LocalDateTime createdSince) {
		this.createdSince = createdSince;
	}
	
}
