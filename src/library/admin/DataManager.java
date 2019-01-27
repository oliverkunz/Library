package library.admin;

import library.data.Actor;
import library.data.Book;
import library.data.BookItem;
import library.data.Customer;
import library.data.Film;
import library.data.FilmItem;
import library.data.Item;
import library.data.Journal;
import library.data.JournalItem;
import library.data.Music;
import library.data.MusicItem;
import library.data.Person;
import library.data.Writer;

public class DataManager {
	private Customer[] customers;
	private BookItem[] bookItems;
	private FilmItem[] filmItems;
	private MusicItem[] musicItems;
	private JournalItem[] journalItems;

	public DataManager() {
		customers = new Customer[20];
		bookItems = new BookItem[30];
		filmItems = new FilmItem[30];
		musicItems = new MusicItem[30];
		journalItems = new JournalItem[30];
	}

	public long[] findItemsByArticleNumber(long ean) throws NoItemsFoundException {
		long[] result = null;
		for (BookItem item : bookItems) {
			if (item.getBook().getArticleNumber() == ean)
				result = Utils.addNumber(result, item.getId());
		}
		for (MusicItem item : musicItems) {
			if (item.getMusic().getArticleNumber() == ean)
				result = Utils.addNumber(result, item.getId());
		}
		for (FilmItem item : filmItems) {
			if (item.getFilm().getArticleNumber() == ean)
				result = Utils.addNumber(result, item.getId());
		}
		for (JournalItem item : journalItems) {
			if (item.getJournal().getArticleNumber() == ean)
				result = Utils.addNumber(result, item.getId());
		}
		if (result == null)
			throw new NoItemsFoundException("ArticleNumber: " + ean);
		return result;
	}

	public Item findItem(long id) throws NoItemsFoundException {
		for (Item item : bookItems) {
			if (item.getId() == id)
				return item;
		}
		for (Item item : musicItems) {
			if (item.getId() == id)
				return item;
		}
		for (Item item : filmItems) {
			if (item.getId() == id)
				return item;
		}
		for (Item item : journalItems) {
			if (item.getId() == id)
				return item;
		}
		throw new NoItemsFoundException("Id: " + id);
	}

	public <T> long[] findItems(Class<T> type, String string) throws NoItemsFoundException {
		long[] result = null;
		if (type.equals(Book.class)) {
			result = findBookItems(string.toLowerCase());
		}
		if (type.equals(Music.class)) {
			result = findMusicItems(string.toLowerCase());
		}
		if (type.equals(Film.class)) {
			result = findFilmItems(string.toLowerCase());
		}
		if (type.equals(Journal.class)) {
			result = findJournalItems(string.toLowerCase());
		}
		if (result == null)
			throw new NoItemsFoundException("Class: " + type + " " + string);
		return result;
	}

	public long[] findItems(String searchString) throws NoItemsFoundException {
		String s = searchString.toLowerCase();
		long[] result = null;
		result = Utils.addNumbers(result, findBookItems(s));
		result = Utils.addNumbers(result, findMusicItems(s));
		result = Utils.addNumbers(result, findFilmItems(s));
		result = Utils.addNumbers(result, findJournalItems(s));
		if (result == null)
			throw new NoItemsFoundException("Search string : " + s);
		return result;
	}

	private long[] findBookItems(String title) {
		long[] result = null;
		for (BookItem item : bookItems) {
			if (item.getBook().getTitle().toLowerCase().equals(title)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		return result;
	}

	private long[] findMusicItems(String s) {
		long[] result = null;
		for (MusicItem item : musicItems) {
			if (item.getMusic().getTitle().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
			if (item.getMusic().getBand().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		return result;
	}

	private long[] findFilmItems(String s) {
		long[] result = null;
		for (FilmItem item : filmItems) {
			if (item.getFilm().getTitle().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
			if (item.getFilm().getPublisher().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		return result;
	}

	private long[] findJournalItems(String s) {
		long[] result = null;
		for (JournalItem item : journalItems) {
			if (item.getJournal().getTitle().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		for (JournalItem item : journalItems) {
			if (item.getJournal().getPublisher().toLowerCase().equals(s)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		return result;
	}

	public long[] findItems(Writer writer) throws NoItemsFoundException {
		long[] result = null;
		for (BookItem item : bookItems) {
			Book book = item.getBook();
			if (book.getWriter().isEqual(writer)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		if (result == null)
			throw new NoItemsFoundException("Book writer: " + writer.toString());
		return result;
	}

	public long[] findItems(Actor actor) throws NoItemsFoundException {
		long[] result = null;
		for (FilmItem item : filmItems) {
			Film film = item.getFilm();
			Actor[] actors = film.getActors();
			if (contains(actors, actor)) {
				result = Utils.addNumber(result, item.getId());
			}
		}
		if (result == null)
			throw new NoItemsFoundException("Film actor: " + actor.toString());
		return result;
	}

	private boolean contains(Actor[] actors, Actor actor) {
		for (Actor a : actors) {
			if (a != null && a.isEqual(actor))
				return true;
		}
		return false;
	}

	public Customer findCustomer(String firstName, String lastName) throws InvalidLoginException {
		for (Customer c : customers) {
			if (new Person(lastName, firstName).isEqual(c))
				return c;
		}
		throw new InvalidLoginException("Kein Kunde mit Name: " + firstName + " " + lastName);
	}

	public Customer[] getCustomers() {
		return customers;
	}

	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	public BookItem[] getBookItems() {
		return bookItems;
	}

	public FilmItem[] getFilmItems() {
		return filmItems;
	}

	public MusicItem[] getMusicItems() {
		return musicItems;
	}

	public JournalItem[] getJournalItems() {
		return journalItems;
	}

}
