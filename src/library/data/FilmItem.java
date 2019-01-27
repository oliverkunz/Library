package library.data;

public class FilmItem extends Item implements Comparable<FilmItem>{
	private Film film;

	public FilmItem(long id, Film film) {
		super(id);
		this.film = film;
	}

	public Film getObject() {
		return getFilm();
	}
	
	public Film getFilm() {
		return film;
	}
	
	public String getTitle() {
		return film.getTitle();
	}

	@Override
	public String toString() {
		return "Film: " + film + super.toString();
	}

	@Override
	public int compareTo(FilmItem o) {
		return film.getTitle().compareTo(o.getFilm().getTitle());
	}
}
