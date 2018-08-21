package app.web.repository.likes;

import java.util.List;

import app.web.repository.model.UserInterest;
import app.web.service.UserLikesRequest;

public interface BaseLikeRepository {

	public List<UserInterest> getUserLikes(UserLikesRequest request);
	
}
