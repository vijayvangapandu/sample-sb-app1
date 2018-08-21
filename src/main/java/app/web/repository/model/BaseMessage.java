package app.web.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "message")
public abstract class BaseMessage<T> {

	public static final String RECIPIENT_USER_ID = "ruid";
	public static final String SENDER_USER_ID = "suid";
	public static final String CREATED_TIMESTAMP = "cts";
	public static final String READ_TIMESTAMP = "rts";
	public static final String MATCHID = "mid";
	public static final String TYPE = "type";
	public static final String ID = "_id";
	public static final String MESSAGE_ID = "msgid";
	public static final String VALUE = "value";

	@Id
	@Field(value = ID)
	private String id;

	@Field(value = MESSAGE_ID)
	private String messageId;

	@Field(value = MATCHID)
	private String matchId;

	@Field(value = TYPE)
	private int type;

	@Field(value = CREATED_TIMESTAMP)
	private LocalDateTime createdTimeStamp;

	@Field(value = READ_TIMESTAMP)
	private LocalDateTime readTimeStamp;

	@Field(value = SENDER_USER_ID)
	private String senderUserId;

	@Field(value = RECIPIENT_USER_ID)
	private String recipientUserId;

	public BaseMessage(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public LocalDateTime getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(LocalDateTime createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	public LocalDateTime getReadTimeStamp() {
		return readTimeStamp;
	}

	public void setReadTimeStamp(LocalDateTime readTimeStamp) {
		this.readTimeStamp = readTimeStamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(String recipientUserId) {
		this.recipientUserId = recipientUserId;
	}
	
	public abstract T getValue();

}
