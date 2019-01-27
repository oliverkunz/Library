package view;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import library.admin.Administration;

public class LoginScreen extends GridPane {

	public LoginScreen() {
		this.setId("loginScreen");
		ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("res/imgk.jpg")));
		Label l1 = new Label("Vorname");
		Label l2 = new Label("Nachname");
		Label l3 = new Label("Email");
		Label l4 = new Label("Passwort");

		Controller controller = Administration.getInstance().getController();
		TextField t1 = new TextField();
		t1.textProperty().bindBidirectional(controller.getFirstName());
		t1.setText("Bruno");
		TextField t2 = new TextField();
		t2.textProperty().bindBidirectional(controller.getLastName());
		t2.setText("Leser1");

		TextField t3 = new TextField();
		t3.textProperty().bindBidirectional(controller.getEmail());
		t3.setText("bruno@leser1.com");
		PasswordField p1 = new PasswordField();
		p1.textProperty().bindBidirectional(controller.getPassword());

		Label l5 = new Label();
		l5.setId("error");
		l5.textProperty().bindBidirectional(controller.getMessage());

		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(15);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setPercentWidth(30);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setPercentWidth(10);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setPercentWidth(10);
		this.getColumnConstraints().addAll(col1, col2, col3, col4);
		this.setGridLinesVisible(false);

		this.add(img, 2, 0, 2, 1);
		this.add(new Label(), 0, 1);
		this.add(l1, 0, 2);
		this.add(t1, 1, 2, 2, 1);
		this.add(l2, 0, 3);
		this.add(t2, 1, 3, 2, 1);
		this.add(l3, 0, 4);
		this.add(t3, 1, 4, 2, 1);
		this.add(l4, 0, 5);
		this.add(p1, 1, 5, 2, 1);
		this.add(l5, 0, 6, 3, 1);
	}
}
