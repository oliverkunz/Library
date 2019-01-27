package library.admin;

public class NoItemsFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public NoItemsFoundException() {
		super("Item not found");
	}

	public NoItemsFoundException(String message) {
		super(message);
	}
}
