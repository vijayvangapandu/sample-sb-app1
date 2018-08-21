package app.web.dto;

import java.util.List;

import app.web.repository.model.Photo;
import app.web.repository.model.UserLocation;

public class Profile {

	private UserDto userDto;
	private UserLocation location;
	private List<Photo> photos;
	
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public UserLocation getLocation() {
		return location;
	}
	public void setLocation(UserLocation location) {
		this.location = location;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	
}
