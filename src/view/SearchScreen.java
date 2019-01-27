package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import library.admin.Administration;

public class SearchScreen extends GridPane implements ChangeListener<String> {
	private Controller controller;
	private Label l4;
	private HBox box1, box2;
	
	public SearchScreen() {
		this.setId("searchScreen");
		this.setMinWidth(750);
		ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("res/imgk.jpg")));
		controller = Administration.getInstance().getController();
		Label l1 = new Label("Kategorie");
		Label l2 = new Label("Suchtext");
		l4 = new Label(" ");
		 
		ChoiceBox<String> t1 = new ChoiceBox<>(controller.getCategories());
		t1.getSelectionModel().selectedItemProperty().addListener(this);

		t1.setTooltip(new Tooltip("Kategorie wählen"));
		t1.setMinWidth(300);

		TextField t2 = new TextField();
		t2.textProperty().bindBidirectional(controller.getTitle());
	    box1 = new HBox();
		TextField t41 = new TextField();
		t41.setPromptText("Nachname");
		TextField t42 = new TextField();
		t42.setPromptText("Vorname");
		t41.textProperty().bindBidirectional(controller.getWriterLastName());
		t42.textProperty().bindBidirectional(controller.getWriterFirstName());
		box1.getChildren().addAll(t41, t42);
		
	    box2 = new HBox(5);
		TextField t51 = new TextField();
		t51.setPromptText("Nachname");
		TextField t52 = new TextField();
		t52.setPromptText("Vorname");
		t51.textProperty().bindBidirectional(controller.getActorLastName());
		t52.textProperty().bindBidirectional(controller.getActorFirstName());
		box2.getChildren().addAll(t51, t52);

		ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton();
		rb1.setToggleGroup(group);
		rb1.setText("Nur Verfügare");
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton();
		rb2.setText("Alle");
		rb2.setToggleGroup(group);

		Label l5 = new Label();
		l5.setId("error");
		l5.textProperty().bindBidirectional(controller.getMessage());
		
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(25);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(25);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(10);
		this.getColumnConstraints().addAll(col1, col2, col3, col4);

		this.add(img, 2, 0, 2, 1);
		this.add(new Label(), 0, 1);
		this.add(l1, 0, 3);
		this.add(t1, 1, 3, 2, 1);
		this.add(l2, 0, 4);
		this.add(t2, 1, 4, 2, 1);
		this.add(l4, 0, 5);
		this.add(rb1, 1, 6, 2, 1);
		this.add(rb2, 2, 6, 2, 1);
		this.add(l5, 0, 7, 3, 1);
	}

	@Override
	public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
		controller.setCategory(newValue);
		if(newValue == "Buch") {
			l4.setText("Schriftsteller/in");
			getChildren().remove(box2);
			add(box1, 1, 5, 2, 1);
		}
		else if(newValue == "Film") {
			l4.setText("Schauspieler/in");
			getChildren().remove(box1);
			add(box2, 1, 5, 2, 1);
		}
		else {
			l4.setText("  ");
			getChildren().remove(box2);
			getChildren().remove(box1);
		}
	}

}
