package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import controller.FiltrirajAtributeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AttributeDialog extends Stage {
	private static AttributeDialog instance=null;
	
	private static TextField pretrazivac;
	private static HBox pretraga;
	private static List<CheckBox> atributi;
	private static CheckBox customAtribut;
	private static VBox boxovi;
	private static HBox buttons;
	private static Button ok;
	private static Button close;
	
	public static CheckBox getCustomAtribut() {
		return customAtribut;
	}

	public static List<CheckBox> getAtributi() {
		return atributi;
	}
	
	public static VBox getBoxovi() {
		return boxovi;
	}
	
	private AttributeDialog() {
		BorderPane pozadina=new BorderPane();
		Scene scena;
		
		pretrazivac=new TextField();
		pretrazivac.textProperty().addListener(new FiltrirajAtributeListener());
		pretraga=new HBox(20);
		pretraga.getChildren().add(pretrazivac);
		pretraga.setAlignment(Pos.CENTER);
		pozadina.setTop(pretraga);
		
		atributi=new ArrayList<CheckBox>();
		Set<String> kljucevi=MainView.getInstance().getOperator().getKljucevi();
		for (String kljuc : kljucevi) {
			atributi.add(new CheckBox(kljuc));
		}
		customAtribut=new CheckBox();
		customAtribut.setVisible(false);
		customAtribut.setManaged(false);
		boxovi=new VBox();
		boxovi.getChildren().addAll(atributi);
		boxovi.getChildren().add(customAtribut);
		boxovi.setAlignment(Pos.CENTER);
		pozadina.setCenter(boxovi);
		
		buttons=new HBox();
		ok=new Button("Ok");
		close=new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
				Stage stage=(Stage)close.getScene().getWindow();
				stage.close();
			}
		});
		buttons.getChildren().addAll(ok, close);
		buttons.setAlignment(Pos.CENTER);
		pozadina.setBottom(buttons);
		
		scena=new Scene(pozadina);
		setScene(scena);
		setWidth(500);
		setHeight(700);
		setTitle("Selekcija atributa entiteta");
		show();
	}

	public static AttributeDialog getInstance() {
		if(instance==null) {
			instance=new AttributeDialog();
		}
		return instance;
	}
}
