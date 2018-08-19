package app.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.web.dto.User;
import app.web.service.UserDataRequest;

@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserResourceRequestHandler requestHandler;
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince) {
		
		List<User> users = new ArrayList<>();
		
		UserDataRequest request = new UserDataRequest();
		request.setDateSince(dateSince);
		request.setPageNumber(pageNumber);
		request.setPageSize(pageSize);
		
		requestHandler.
		return users;
		
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void getAllUsers(@Suspended final AsyncResponse asyncResponse,
			final @Context HttpHeaders headers,
			@QueryParam("pageNumber") final Integer pageNumber, 
			@QueryParam("pageSize") final Integer pageSize, 
			@QueryParam("dateSince") final Long dateSince) {
		
		UserDataRequest request = new UserDataRequest();
		request.setDateSince(dateSince);
		request.setPageNumber(pageNumber);
		request.setPageSize(pageSize);
		
		requestHandler.handleGetUserRequest(request, asyncResponse);
		
	}
}
