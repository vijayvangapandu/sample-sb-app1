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

import app.web.dto.UserDto;
import app.web.repository.model.User;
import app.web.service.UserDataRequest;

@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserResourceRequestHandler requestHandler;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void getAllUsers(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince,
			@QueryParam("gender") final Integer gender) {
		
		UserDataRequest request = new UserDataRequest();
		request.setDateSince(dateSince);
		request.setPageNumber(pageNumber);
		request.setPageSize(pageSize);
		request.setGender(gender);
		
		requestHandler.handleGetUserRequest(request, asyncResponse);
		
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(final @Context HttpHeaders headers,
			@PathParam("userId") final String userId) {
		
		return requestHandler.getUser(userId);
		
	}
	
	@GET
	@Path("/public/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserDto getUserDto(final @Context HttpHeaders headers,
			@PathParam("userId") final String userId) {
		
		return requestHandler.getUserDto(userId);
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public User saveUser(final @Context HttpHeaders headers, User user) {
		return requestHandler.saveUser(user);
		
	}
}
