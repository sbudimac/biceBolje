package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import api.Operacija;
import api.Uslov;
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
	private List<HBox> redovi;
	
	private List<ComboBox<String>> kljucevi;
	private List<ComboBox<String>> operacije;
	private List<TextField> uslovi;
	private List<CheckBox> ugnjezdeni;
	
	private HBox buttons;
	
	private Button ok;
	private Button cancel;
	
	public FilterDialog() {
		super();
		BorderPane pozadina=new BorderPane();
		
		redovi=new ArrayList<>();
		kljucevi=new ArrayList<>();
		operacije=new ArrayList<>();
		uslovi=new ArrayList<>();
		ugnjezdeni=new ArrayList<>();
		sviUslovi=new VBox();
		sviUslovi.getChildren().addAll(redovi);
		
		noviUslov();
		
		buttons=new HBox();
		ok=new Button("Potvrdi");
		cancel=new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)cancel.getScene().getWindow();
				stage.close();
			}
		});
		buttons.getChildren().add(ok);
		buttons.getChildren().add(cancel);
		
		pozadina.setCenter(sviUslovi);
		pozadina.setBottom(buttons);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(400);
		getDialogPane().setPrefHeight(600);
		setTitle("Odabir uslova pretrage");
	}
	
	public void noviUslov() {
		listaKljuceva=MainView.getInstance().getSkladiste().getKljucevi();
		
		ComboBox<String> cbKljuc=new ComboBox<String>();
		cbKljuc.getItems().addAll(listaKljuceva);
		kljucevi.add(cbKljuc);
		
		ComboBox<String> cbUslov=new ComboBox<String>();
		List<String> enums=Stream.of(Operacija.values()).map(Operacija::name).collect(Collectors.toList());
		cbUslov.getItems().addAll(enums);
		operacije.add(cbUslov);
		
		TextField tf=new TextField();
		uslovi.add(tf);
		
		CheckBox cb=new CheckBox();
		ugnjezdeni.add(cb);
		
		HBox uslov=new HBox(cbKljuc, cbUslov, tf, cb);
		redovi.add(uslov);
		sviUslovi.getChildren().removeAll();
		sviUslovi.getChildren().addAll(redovi);
	}
}
