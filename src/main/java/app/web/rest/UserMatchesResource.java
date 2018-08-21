package app.web.rest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import app.web.repository.model.UserMatch;
import app.web.service.UserMatchesRequest;

@Component
@Path("/v1/users")
public class UserMatchesResource {

	@Autowired
	private UserMatchesResourceRequestHandler requestHandler;
	
	@GET
	@Path("/{userId}/matches")
	@Produces(MediaType.APPLICATION_JSON)
	public void getUserMatches(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@PathParam("userId") final String userId,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince,
			@QueryParam("state") final Integer matchState) {
		
		UserMatchesRequest request = new UserMatchesRequest();
		request.setUserId(userId);
		request.setMatchState(Optional.ofNullable(matchState));
		
		if(dateSince != null) {
			LocalDateTime createdSince =
				    LocalDateTime.ofInstant(Instant.ofEpochMilli(dateSince), ZoneId.systemDefault());
			request.setCreatedSince(createdSince);
		}
		request.setPageNumber(pageNumber);
		request.setPageSize(pageSize);
		
		requestHandler.handleGetUserMatchesRequest(request, asyncResponse);
		
	}
	
	@GET
	@Path("/{userId}/matches/{matchId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserMatch getUserMatch(final @Context HttpHeaders headers,
			@PathParam("userId") final String userId,
			@PathParam("matchId") final String matchId) {
		
		return requestHandler.getUserMatch(userId, matchId);
		
	}
	
	@POST
	@Path("/{userId}/matches")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UserMatch saveUserMatch(final @Context HttpHeaders headers, UserMatch userMatch) {
		return requestHandler.saveUserMatch(userMatch);
	}
	
	//TODO - Create PUT request change the match state
}
