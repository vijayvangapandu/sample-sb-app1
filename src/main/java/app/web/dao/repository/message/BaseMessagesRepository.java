
package app.web.dao.repository.message;

import java.time.LocalDateTime;
import java.util.List;

import app.web.dao.model.BaseMessage;

public interface BaseMessagesRepository {

	/**
	 * Return the last exchanged message between the match.
	 * 
	 * @param matchId
	 *            Id of the match
	 * @return Most recent message exchanged between the match
	 */
	BaseMessage getLastExchangedMessage(long matchId);
	
	/**
	 * Resets the read time stamp for the messages received by the user from a particular match.
	 * This method can also perform update on specific messages based on the IDs provided.
	 * 
	 * @param userId Id of the user
	 * @param matchId Id of the match
	 * @param messageIds [Optional]Id of the messages whose read time stamp needs to be reset.
	 * 
	 * @return number of records updated
	 */
	int resetReadTimeStampForUserMatchAndMessageIds(long userId, long matchId, List<String> messageIds);
	
	/**
	 * Sets the read time stamp for the messages received by the user from a particular match.
	 * This method can also perform update on specific messages based on the IDs provided.
	 * 
	 * @param userId Id of the user
	 * @param matchId Id of the match
	 * @param messageIds  [Optional]Ids of the messages whose read time stamp needs to be updated.
	 * @param readTimeStamp Time stamp when the message was read.
	 * 
	 * @return number of records updated
	 */
	int setReadTimeStampForUserMatchAndMessageIds(long userId, long matchId, List<String> messageIds, LocalDateTime readTimeStamp);
	
	
	/**
	 * Retrieves messages that were exchanged between the users part of the match.
	 * 
	 * @param userId Id of the user
	 * @param matchId Id of the match
	 * @param messageType Type of message to filter by
	 * @return All messages that were exchanged as part of the match.
	 */
	List<BaseMessage> getMessagesForUserAndMatch(long userId, long matchId, int messageType);
	
	
	
	/**
	 * Returns the count of messages that are unread by the user and received from the match.
	 * 
	 * @param userId Id of the user
	 * @param matchId Id of the match
	 * @return The count of unread messages received by the user from the match.
	 */
	Long getUnreadMessagesCountForUserAndMatch(long userId, long matchId);
	
	/**
	 * Update the message text for a given user as a sender of the message.
	 * @param userId to update the message for
	 * @param messageText with which to update the message
	 * @return the number of messages updated
	 */
	int updateMessageTextBySenderUserId(long userId, String messageText);

	/**
	 * Update the message text for a given user as a receiver of the message.
	 * @param userId to update the message for
	 * @param messageText with which to update the message
	 * @return the number of messages updated
	 */
	int updateMessageTextByRecipientUserId(long userId, String messageText) ;

	
}
