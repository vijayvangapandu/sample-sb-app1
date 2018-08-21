package app.web.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import app.web.repository.model.enums.LikeStatus;

@Document(collection="user_swipes")
public class UserInterest {

	public static final String ID = "_id";
	public static final String CREATED_TIMESTAMP = "cts";
	public static final String DECLINED_TIMESTAMP = "dts";
	public static final String MUTUAL_LIKE_TIMESTAMP = "mts";
	public static final String USER_ID = "uid";
	public static final String OTHER_USER_ID = "ouid";
	public static final String LIKE_STATUS = "status";
	
	@Id
	@Field(value = ID)
	private String id;
	
	@Field(value = USER_ID)
	private String userId;
	
	@Field(value = OTHER_USER_ID)
	private String otherUserId;
	
	@Field(value = CREATED_TIMESTAMP)
	private LocalDateTime swipeDate;
	
	@Field(value = LIKE_STATUS)
	private LikeStatus status;
	
	@Field(value = DECLINED_TIMESTAMP)
	private LocalDateTime declinedDate;
	
	@Field(value = MUTUAL_LIKE_TIMESTAMP)
	private LocalDateTime mutualLikeDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOtherUserId() {
		return otherUserId;
	}
	public void setOtherUserId(String otherUserId) {
		this.otherUserId = otherUserId;
	}
	public LocalDateTime getSwipeDate() {
		return swipeDate;
	}
	public void setSwipeDate(LocalDateTime swipeDate) {
		this.swipeDate = swipeDate;
	}
	public LikeStatus getStatus() {
		return status;
	}
	public void setStatus(LikeStatus status) {
		this.status = status;
	}
	public LocalDateTime getDeclinedDate() {
		return declinedDate;
	}
	public void setDeclinedDate(LocalDateTime declinedDate) {
		this.declinedDate = declinedDate;
	}
	public static String getCreatedTimestamp() {
		return CREATED_TIMESTAMP;
	}
	public static String getDeclinedTimestamp() {
		return DECLINED_TIMESTAMP;
	}
	public static String getLikeStatus() {
		return LIKE_STATUS;
	}
	public LocalDateTime getMutualLikeDate() {
		return mutualLikeDate;
	}
	public void setMutualLikeDate(LocalDateTime mutualLikeDate) {
		this.mutualLikeDate = mutualLikeDate;
	}
	public static String getMutualLikeTimestamp() {
		return MUTUAL_LIKE_TIMESTAMP;
	}
	
}
