package library.data;

public class BookItem extends Item implements Comparable<BookItem> {
	private Book book;

	public BookItem(long id, Book book) {
		super(id);
		this.book = book;
	}	

	public Book getBook() {
		return book;
	}

	public Book getObject() {
		return getBook();
	}
	
	
	@Override
	public String toString() {
		return "Book: " + book + super.toString();
	}

	@Override
	public int compareTo(BookItem o) {
		return book.getTitle().compareTo(o.getBook().getTitle());
	}
}
