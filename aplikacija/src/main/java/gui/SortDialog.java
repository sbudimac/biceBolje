package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.DodajKoloneAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SortDialog extends Stage {
	
	private CheckBox naziv;
	private CheckBox id;
	private List<CheckBox> atributi;
	private VBox boxovi;
	private HBox buttons;
	private Button sort;
	private Button close;
	
	public SortDialog() {
		BorderPane pozadina=new BorderPane();
		Scene scena;
		
		naziv=new CheckBox("Naziv");
		id=new CheckBox("Id");
		atributi=new ArrayList<>();
		atributi.add(naziv);
		atributi.add(id);
		Set<String> kljucevi=MainView.getInstance().getSkladiste().getKljucevi();
		for (String kljuc : kljucevi) {
			atributi.add(new CheckBox(kljuc));
		}
		
		boxovi=new VBox();
		boxovi.getChildren().addAll(atributi);
		boxovi.setAlignment(Pos.CENTER);
		pozadina.setCenter(boxovi);
		
		buttons=new HBox();
		sort=new Button("Sort");
		sort.setOnAction(new DodajKoloneAction());
		close=new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)close.getScene().getWindow();
				stage.close();
			}
		});
		buttons.getChildren().addAll(sort, close);
		buttons.setAlignment(Pos.CENTER);
		pozadina.setBottom(buttons);
		
		scena=new Scene(pozadina);
		setScene(scena);
		setWidth(500);
		setHeight(700);
		setTitle("Sortiranje entiteta");
		show();
	}
}
