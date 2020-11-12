package gui;

import java.util.ArrayList;
import java.util.List;

import api.Entitet;
import controller.DodajUgnjezdeniAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DodavanjeUgnjezdenogDialog extends Dialog<Entitet> {
	private Label lbIds;
	private Label lbNaziv;
	private Label lbId;
	private Label lbKljuc;
	private ComboBox<String> ids;
	private TextField naziv;
	private TextField id;
	private TextField kljuc;
	private Button ok;
	private Button cancel;
	private HBox hbIds;
	private HBox buttoni;
	private HBox tfBox;
	
	public DodavanjeUgnjezdenogDialog() {
		super();
		BorderPane pozadina=new BorderPane();
		
		lbIds=new Label("Lista id-jeva spoljasnjih entiteta: ");
		lbNaziv=new Label("Naziv entiteta: ");
		lbId=new Label("Id entiteta: ");
		lbKljuc=new Label("Kljuc spoljasnjeg entiteta: ");
		
		ids=new ComboBox<String>();
		List<String> idjevi=new ArrayList<>();
		for(Entitet entitet : MainView.getInstance().getSkladiste().getEntiteti()) {
			idjevi.add(entitet.getId());
		}
		ids.getItems().setAll(idjevi);
		hbIds=new HBox(lbIds, ids);
		
		naziv=new TextField();
		id=new TextField();
		kljuc=new TextField();
		tfBox=new HBox(lbNaziv, naziv, lbId, id, lbKljuc, kljuc);
		
		ok=new Button("Ok");
		ok.setOnAction(new DodajUgnjezdeniAction(this));
		cancel=new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)cancel.getScene().getWindow();
				stage.close();
			}
		});
		buttoni=new HBox(ok, cancel);
		
		pozadina.setTop(hbIds);
		pozadina.setCenter(tfBox);
		pozadina.setBottom(buttoni);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(800);
		getDialogPane().setPrefHeight(100);
		setTitle("Odabir id-ja spoljasnjeg entiteta");
	}
	
	public ComboBox<String> getIds(){
		return ids;
	}
	
	public TextField getNaziv() {
		return naziv;
	}
	
	public TextField getId() {
		return id;
	}
	
	public TextField getKljuc() {
		return kljuc;
	}
	
	public Button getOk() {
		return ok;
	}
}
