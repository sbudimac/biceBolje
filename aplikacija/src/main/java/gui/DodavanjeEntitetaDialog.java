package gui;

import api.Entitet;
import controller.DodavanjeEntitetaAction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DodavanjeEntitetaDialog extends Dialog<Entitet> {
	private Label lbNaziv;
	private Label lbId;
	private TextField tfNaziv;
	private TextField tfId;
	private HBox hbTf;
	
	private Button ok;
	private Button cancel;
	private HBox hbBt;
	
	public DodavanjeEntitetaDialog() {
		BorderPane pozadina=new BorderPane();
		
		lbNaziv=new Label("Naziv entiteta: ");
		lbId=new Label("Id entiteta: ");
		tfNaziv=new TextField();
		tfId=new TextField();
		hbTf=new HBox(lbNaziv, tfNaziv, lbId, tfId);
		
		ok=new Button("ok");
		ok.setOnAction(new DodavanjeEntitetaAction(tfNaziv, tfId, this));
		cancel=new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)cancel.getScene().getWindow();
				stage.close();
			}
		});
		hbBt=new HBox(ok, cancel);
		
		pozadina.setCenter(hbTf);
		pozadina.setBottom(hbBt);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(500);
		getDialogPane().setPrefHeight(100);
		setTitle("Odabir uslova pretrage");
	}
	
	public DodavanjeEntitetaDialog(String id) {
		BorderPane pozadina=new BorderPane();
		
		lbNaziv=new Label("Naziv entiteta: ");
		lbId=new Label("Id entiteta: ");
		tfNaziv=new TextField();
		tfId=new TextField(id);
		hbTf=new HBox(lbNaziv, tfNaziv, lbId, tfId);
		
		ok=new Button("ok");
		ok.setOnAction(new DodavanjeEntitetaAction(tfNaziv, tfId, this));
		cancel=new Button("Cancel");
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)cancel.getScene().getWindow();
				stage.close();
			}
		});
		hbBt=new HBox(ok, cancel);
		
		pozadina.setCenter(hbTf);
		pozadina.setBottom(hbBt);
		
		getDialogPane().setContent(pozadina);
		getDialogPane().setPrefWidth(500);
		getDialogPane().setPrefHeight(100);
		setTitle("Odabir uslova pretrage");
	}
	
	public Button getOk() {
		return ok;
	}
}
