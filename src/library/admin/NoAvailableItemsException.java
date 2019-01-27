package library.admin;

public class NoAvailableItemsException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public NoAvailableItemsException() {
		super("No available item found");
	}

	public NoAvailableItemsException(long[] ids) {
		message = "Ids";
		for (long id : ids)
			message += " " + id;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
