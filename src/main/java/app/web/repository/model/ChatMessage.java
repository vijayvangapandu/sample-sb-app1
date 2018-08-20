
package app.web.repository.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class ChatMessage extends BaseMessage {

	public ChatMessage() {
		super(1);
	}

	@Field(value = VALUE)
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
