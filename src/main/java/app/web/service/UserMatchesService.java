package app.web.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.web.repository.match.MatchRepository;
import app.web.repository.model.UserMatch;
import rx.Observable;

@Service
public class UserMatchesService {

	@Autowired
	private MatchRepository matchRepository;

	public Observable<List<UserMatch>> getUserMatches(UserMatchesRequest request) {
		return Observable.defer(() -> Observable.just(getUserMatchesList(request)));
	}

	private List<UserMatch> getUserMatchesList(UserMatchesRequest request) {
		List<UserMatch> matches = matchRepository.getUserMatches(request);
		if (CollectionUtils.isNotEmpty(matches)) {
			return matches;
		}
		return Lists.emptyList();
	}

	public UserMatch saveUserMatch(UserMatch userMatch) {
		return matchRepository.save(userMatch);
	}

	public UserMatch getUserMatchById(String matchId) {
		return matchRepository.findOne(matchId);
	}

	public UserMatch getUserMatch(String userId, String matchId) {
		return matchRepository.getUserMatch(userId, matchId);
	}
	
	

}
