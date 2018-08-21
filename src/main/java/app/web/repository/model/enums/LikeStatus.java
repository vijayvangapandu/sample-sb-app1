package app.web.repository.model.enums;

public enum LikeStatus {

	LIKE_INITIATED(1, "LIKE_SEND"), LIKE_DECLINED(2, "LIKE_DECLINED"), MUTUAL_LIKE(3, "MUTUAL_LIKE");
	
	int code;
	String name;
	
	private LikeStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
}
