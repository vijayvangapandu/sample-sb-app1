package app.web.external.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import app.web.dto.UserDto;

@Service
public class FourSquareClient {

	@Autowired
	private RestTemplate restTemplate;
	
	public FourSquareClient() {
		//this.restTemplate = new RestTemplate();
	}
	
	public List<UserDto> getUsers() {
		
		ResponseEntity<List<UserDto>> rateResponse =
		        restTemplate.exchange("http://localhost:7001/interview/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDto>>() {
		            });
		List<UserDto> users = rateResponse.getBody();
		
		return users;
	}
	
}
