package gui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import api.Operacija;
import api.Uslov;
import controller.DodajUslovAction;
import controller.PotvrdiUsloveAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FilterDialog extends Dialog<List<Uslov>> {
	private Set<String> listaKljuceva;
	
	private VBox sviUslovi;
	private Set<FilterGUI> redovi;
	
	private List<ComboBox<String>> kljucevi;
	private List<ComboBox<Operacija>> operacije;
	private List<TextField> uslovi;
	
	private List<CheckBox> ugnjezdeni;
	private List<ComboBox<String>> spoljasnjiKljucevi;
	
	private HBox buttons;
	
	private Button ok;
	private Button dodaj;
	private Button cancel;
	
	public FilterDialog() {
		super();
		BorderPane pozadina=new BorderPane();
		
		redovi=new HashSet<>();
		kljucevi=new ArrayList<>();
		operacije=new ArrayList<>();
		uslovi=new ArrayList<>();
		ugnjezdeni=new ArrayList<>();
		spoljasnjiKljucevi=new ArrayList<>();
		sviUslovi=new VBox();
		sviUslovi.getChildren().addAll(redovi);
		
		noviUslov();
		
		buttons=new HBox();
		ok=new Button("Potvrdi");
		ok.setOnAction(new PotvrdiUsloveAction(this));
		dodaj=new Button("Dodaj");
		dodaj.setOnAction(new DodajUslovAction(this));
		cancel=new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)cancel.getScene().getWindow();
				stage.close();
			}
		});
		buttons.getChildren().add(ok);
		buttons.getChildren().add(dodaj);
		buttons.getChildren().add(cancel);
		
		pozadina.setCenter(sviUslovi);
		pozadina.setBottom(buttons);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(800);
		getDialogPane().setPrefHeight(600);
		setTitle("Odabir uslova pretrage");
	}
	
	public void noviUslov() {
		listaKljuceva=MainView.getInstance().getSkladiste().getKljucevi();
		
		ComboBox<String> cbKljuc=new ComboBox<String>();
		cbKljuc.getItems().addAll(listaKljuceva);
		kljucevi.add(cbKljuc);
		
		ComboBox<Operacija> cbUslov=new ComboBox<Operacija>();
		cbUslov.getItems().setAll(Operacija.values());
		operacije.add(cbUslov);
		
		TextField tf=new TextField();
		uslovi.add(tf);
		
		CheckBox cb=new CheckBox();
		ugnjezdeni.add(cb);
		
		ComboBox<String> cbSpoljasnji=new ComboBox<String>();
		cbSpoljasnji.getItems().setAll(listaKljuceva);
		cbSpoljasnji.setVisible(false);
		cbSpoljasnji.setManaged(false);
		spoljasnjiKljucevi.add(cbSpoljasnji);
		
		FilterGUI uslov=new FilterGUI(cbKljuc, cbUslov, tf, cb, cbSpoljasnji);
		redovi.add(uslov);
		sviUslovi.getChildren().addAll(uslov);
	}
	
	public Set<FilterGUI> getRedovi(){
		return redovi;
	}
	
	public Button getOk() {
		return ok;
	}
}
