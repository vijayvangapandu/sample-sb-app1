package app.web.repository.model.enums;

public enum MatchState {
	NEW(1, "NEW"), COMMUNICATION(2, "COMMUNICATION"), CLOSED(3, "CLOSED");

	private final int id;
	private final String name;

	private MatchState(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	};

	public MatchState fromId(int id) {
		for (MatchState matchState : values()) {
			if (matchState.getId() == id) {
				return matchState;
			}
		}
		return null;
	}
}
