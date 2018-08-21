
package app.web.repository.message;

import java.util.List;

import app.web.repository.model.BaseMessage;

@SuppressWarnings("rawtypes")
public interface BaseMessagesRepository {

	BaseMessage getLastExchangedMessage(long matchId);

	List<BaseMessage> getMessagesForUserAndMatch(long userId, long matchId, int messageType);

	Long getUnreadMessagesCountForUserAndMatch(long userId, long matchId);

}
