package app.web.rest;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.repository.model.UserInterest;
import app.web.service.UserLikesRequest;

@Component
@Path("/v1/swipes")
public class UserLikesResource {

	@Autowired
	private UserLikesResourceRequestHandler requestHandler;
	
	@GET
	@Path("/users/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getUserOwnSwipes(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@PathParam("userId") final String userId,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince,
			@QueryParam("state") final Integer matchState) {
		
		UserLikesRequest request = new UserLikesRequest();
		request.setUserId(Optional.ofNullable(userId));
		requestHandler.handleGetUserLikesRequest(request, asyncResponse);
		
	}
	
	@GET
	@Path("/otherUsers/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getUserSwipesByOther(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@PathParam("userId") final String userId,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince,
			@QueryParam("state") final Integer matchState) {
		
		UserLikesRequest request = new UserLikesRequest();
		request.setOtherUserId(Optional.ofNullable(userId));
		requestHandler.handleGetUserLikesRequest(request, asyncResponse);
		
	}
	
	@POST
	@Path("/left/users/{userId}/otherUser/{otherUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserInterest swipeLeft(final @Context HttpHeaders headers, 
			@PathParam("userId") final String swipedUserId,
			@PathParam("otherUserId") final String otherUserId) {
		return requestHandler.swipeLeft(swipedUserId, otherUserId);
	}
	
	@POST
	@Path("/right/users/{userId}/otherUser/{otherUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserInterest swipeRight(final @Context HttpHeaders headers, 
			@PathParam("userId") final String swipedUserId,
			@PathParam("otherUserId") final String otherUserId) {
		return requestHandler.swipeRight(swipedUserId, otherUserId);
	}
	
	//TODO - Create PUT request change the match state
}
