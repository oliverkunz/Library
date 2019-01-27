package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import library.admin.Administration;
import library.data.Item;

public class TableScreen extends BorderPane implements ChangeListener<Item> {
	TableView<Item> table;

	public TableScreen() {

		this.setId("tableScreen");

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.TOP_RIGHT);
		ImageView img = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("res/imgk.jpg")));
		hbox.getChildren().add(img);

		VBox vbox = new VBox(10.0);
		vbox.setMinHeight(100);
		vbox.setMaxHeight(250);

		table = new TableView<Item>();
		TableColumn<Item, Long> idCol = new TableColumn<Item, Long>("Id");
		idCol.setCellValueFactory(new PropertyValueFactory<Item, Long>("id"));
		idCol.setMinWidth(180);
		TableColumn<Item, String> titleCol = new TableColumn<Item, String>("Title");
		titleCol.setCellValueFactory(new PropertyValueFactory<Item, String>("title"));
		titleCol.setMinWidth(250);
		table.getColumns().add(idCol);
		table.getColumns().add(titleCol);

		table.getSelectionModel().selectedItemProperty().addListener(this);
		vbox.getChildren().add(table);
		vbox.setAlignment(Pos.CENTER);
		this.setTop(hbox);
		this.setCenter(vbox);
	}

	public TableView<Item> getTable() {
		return table;
	}

	@Override
	public void changed(ObservableValue<? extends Item> ov, Item oldValue, Item newValue) {
		if (table.getSelectionModel().getSelectedItem() != null) {
			Controller controller = Administration.getInstance().getController();
			controller.setSelectedItem(newValue);
			controller.nextScreen();
		}
	}

}
