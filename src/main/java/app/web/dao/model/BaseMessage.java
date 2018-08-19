package app.web.dao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "message")
public abstract class BaseMessage {

	public static final String RECIPIENT_USER_ID = "ruid";

	public static final String SENDER_USER_ID = "suid";

	public static final String CREATED_TIMESTAMP = "cts";

	public static final String READ_TIMESTAMP = "rts";

	public static final String MATCHID = "mid";

	public static final String TYPE = "type";
	
	public static final String ID = "_id";
	
	public static final String MESSAGE_ID = "msgid";
	
	public static final String CORRELATION_META_DATA = "cmd";
	public static final String VALUE = "val";

	public static final String BATCH_ID = "bid";

	@Id
	@Field(value = ID)
	private String id;
	
	@Field(value = MESSAGE_ID)
	private String messageId;

	@Field(value = MATCHID)
	private Long matchId;

	@Field(value = TYPE)
	private int type;

	@Field(value = CREATED_TIMESTAMP)
	private LocalDateTime createdTimeStamp;

	@Field(value = READ_TIMESTAMP)
	private LocalDateTime readTimeStamp;

	@Field(value = SENDER_USER_ID)
	private Long senderUserId;

	@Field(value = RECIPIENT_USER_ID)
	private Long recipientUserId;
	
	

	@Field(value = BATCH_ID)
	private String batchId;

	public BaseMessage(int type) {
		this.type = type;
	}
	
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public int getType() {
		return type;
	}

	public Long getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(Long senderUserId) {
		this.senderUserId = senderUserId;
	}

	public Long getRecipientUserId() {
		return recipientUserId;
	}

	public void setRecipientUserId(Long recipientUserId) {
		this.recipientUserId = recipientUserId;
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

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
