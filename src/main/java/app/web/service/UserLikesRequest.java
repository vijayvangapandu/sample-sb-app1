package app.web.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import app.web.repository.model.UserInterest;
import app.web.repository.model.enums.LikeStatus;

public class UserLikesRequest {

	private Optional<String> userId;
	private Optional<String> otherUserId;
	private Optional<LikeStatus> likeStatus;
	private Optional<Integer> pageNumber;
	private Optional<Integer> pageSize;
	private LocalDateTime createdSince;
	
	private List<UserInterest> userLikes;
	
	public List<UserInterest> getUserLikes() {
		return userLikes;
	}
	public void setUserLikes(List<UserInterest> userLikes) {
		this.userLikes = userLikes;
	}
	public Optional<LikeStatus> getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(Optional<LikeStatus> likeStatus) {
		this.likeStatus = likeStatus;
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
	
	public LocalDateTime getCreatedSince() {
		return createdSince;
	}
	public void setCreatedSince(LocalDateTime createdSince) {
		this.createdSince = createdSince;
	}
	public void setPageNumber(Optional<Integer> pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Optional<String> getUserId() {
		return userId;
	}
	public void setUserId(Optional<String> userId) {
		this.userId = userId;
	}
	public Optional<String> getOtherUserId() {
		return otherUserId;
	}
	public void setOtherUserId(Optional<String> otherUserId) {
		this.otherUserId = otherUserId;
	}
	
	
}
