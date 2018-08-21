package app.web.repository.match;

import java.util.List;

import app.web.repository.model.UserMatch;
import app.web.service.UserMatchesRequest;

public interface BaseMatchRepository {

	public List<UserMatch> getUserMatches(UserMatchesRequest request);
	
	public UserMatch getUserMatch(String userId, String matchId);
	
}
