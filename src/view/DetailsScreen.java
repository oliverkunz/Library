package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import library.admin.Administration;
import library.data.Book;
import library.data.BookItem;
import library.data.Film;
import library.data.FilmItem;
import library.data.Item;
import library.data.Journal;
import library.data.JournalItem;
import library.data.Music;
import library.data.MusicItem;

public class DetailsScreen extends BorderPane {
	private Controller controller;
	public DetailsScreen() {
		this.setId("detailsScreen");
		HBox hbox = new HBox();
		this.setTop(hbox);
		hbox.setAlignment(Pos.TOP_RIGHT);
		ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("res/imgk.jpg")));
		hbox.getChildren().add(img);
		hbox.setPadding(new Insets(0, 0, 50, 0));
	}

	public void setContent(Item selectedItem) {
		controller = Administration.getInstance().getController();
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(3, 5, 3, 5));
		pane.setId("pane");
		this.setCenter(pane);

		if (selectedItem != null) {
			Label l11 = new Label("Id");
			pane.add(l11, 0, 0);
			Label l12 = new Label(String.valueOf(selectedItem.getId()));
			pane.add(l12, 1, 0);

			Label l21 = new Label("Title");
			pane.add(l21, 0, 1);
			Label l22 = new Label(selectedItem.getTitle());
			pane.add(l22, 1, 1);

			Label l31, l32, l41, l42;
			if (selectedItem instanceof JournalItem) {
				Journal journal = ((JournalItem) selectedItem).getJournal();
				l31 = new Label("Verlag");
				l32 = new Label(journal.getPublisher());
				l41 = new Label("Seiten");
				l42 = new Label(String.valueOf(journal.getPages()));
			} else if (selectedItem instanceof MusicItem) {
				Music music = ((MusicItem) selectedItem).getMusic();
				l31 = new Label("Band");
				l32 = new Label(music.getBand());
				l41 = new Label("Länge");
				l42 = new Label(String.valueOf(music.getLength()));
			} else if (selectedItem instanceof FilmItem) {
				Film film = ((FilmItem) selectedItem).getFilm();
				l31 = new Label("Schauspieler/in");
				l32 = new Label(film.getActors()[0].toString());
				l41 = new Label("Länge");
				l42 = new Label(String.valueOf(film.getLength()));
			} else {
				Book book = ((BookItem) selectedItem).getBook();
				l31 = new Label("Schriftsteller/in");
				l32 = new Label(book.getWriter().toString());
				l41 = new Label("Länge");
				l42 = new Label(String.valueOf(book.getPages()));
			}
			pane.add(l31, 0, 2);
			pane.add(l32, 1, 2);
			pane.add(l41, 0, 3);
			pane.add(l42, 1, 3);
			Label l5 = new Label();
			l5.setId("info");
			l5.textProperty().bindBidirectional(controller.getMessage());
			pane.add(new Label(), 0, 4);
			pane.add(l5, 0, 5, 2, 1);
		}
	}
}
