package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.DodajKoloneAction;
import controller.FiltrirajAtributeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AttributeDialog extends Dialog<Boolean> {	
	private TextField pretrazivac;
	private HBox pretraga;
	private List<CheckBox> atributi;
	private CheckBox customAtribut;
	private VBox boxovi;
	private HBox buttons;
	private Button ok;
	private Button close;
	
	
	public CheckBox getCustomAtribut() {
		return customAtribut;
	}

	public List<CheckBox> getAtributi() {
		return atributi;
	}
	
	public VBox getBoxovi() {
		return boxovi;
	}
	
	public AttributeDialog() {
		super();
		BorderPane pozadina=new BorderPane();
		
		pretrazivac=new TextField();
		pretrazivac.textProperty().addListener(new FiltrirajAtributeListener(this));
		pretraga=new HBox(20);
		pretraga.getChildren().add(pretrazivac);
		pretraga.setAlignment(Pos.CENTER);
		pozadina.setTop(pretraga);
		
		atributi=new ArrayList<CheckBox>();
		Set<String> kljucevi=MainView.getInstance().getSkladiste().getKljucevi();
		for (String kljuc : kljucevi) {
			atributi.add(new CheckBox(kljuc));
		}
		customAtribut=new CheckBox();
		customAtribut.setVisible(false);
		customAtribut.setManaged(false);
		atributi.add(customAtribut);
		boxovi=new VBox();
		boxovi.getChildren().addAll(atributi);
		boxovi.setAlignment(Pos.CENTER);
		pozadina.setCenter(boxovi);
		
		buttons=new HBox();
		ok=new Button("Ok");
		ok.setOnAction(new DodajKoloneAction(this));
		close=new Button("Cancel");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)close.getScene().getWindow();
				stage.close();
			}
		});
		buttons.getChildren().addAll(ok, close);
		buttons.setAlignment(Pos.CENTER);
		pozadina.setBottom(buttons);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(200);
		getDialogPane().setPrefHeight(600);
		setTitle("Selekcija atributa entiteta");
	}
}
