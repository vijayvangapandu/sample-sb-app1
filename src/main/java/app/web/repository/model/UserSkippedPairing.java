package app.web.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="user_skipped_pairings")
public class UserSkippedPairing {

	public static final String ID = "_id";
	public static final String CREATED_TIMESTAMP = "cts";
	public static final String USER_ID = "uid";
	public static final String OTHER_USER_ID = "ouid";
	
	@Id
	@Field(value = ID)
	private String id;
	
	@Field(value = USER_ID)
	private String userId;
	
	@Field(value = OTHER_USER_ID)
	private String matchedUserId;
	
	@Field(value = CREATED_TIMESTAMP)
	private LocalDateTime matchedDate;
	
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
	public String getMatchedUserId() {
		return matchedUserId;
	}
	public void setMatchedUserId(String matchedUserId) {
		this.matchedUserId = matchedUserId;
	}
	public LocalDateTime getMatchedDate() {
		return matchedDate;
	}
	public void setMatchedDate(LocalDateTime matchedDate) {
		this.matchedDate = matchedDate;
	}
	
}
