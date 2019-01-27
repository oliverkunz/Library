package library.admin;

import library.data.Item;

public class ItemNotAvailableException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;

	public ItemNotAvailableException() {
		super("Item is not available");
	}

	public ItemNotAvailableException(Item item) {
			super("Item " + item.getId() + "not available");
	}

	@Override
	public String getMessage() {
		return message;
	}
}
