package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AttributeDialog extends Stage {
	private static AttributeDialog instance=null;
	
	private TextField pretrazivac;
	private HBox pretraga;
	private List<CheckBox> atributi;
	private VBox boxovi;
	
	private AttributeDialog() {
		BorderPane pozadina=new BorderPane();
		
		pretrazivac=new TextField();
		pretraga=new HBox(20);
		pretraga.getChildren().add(pretrazivac);
		pozadina.setTop(pretraga);
		
		atributi=new ArrayList<CheckBox>();
		Set<String> kljucevi=MainView.getInstance().getOperator().getKljucevi();
		for (String kljuc : kljucevi) {
			atributi.add(new CheckBox(kljuc));
		}
		System.out.println(atributi.size());
		boxovi.getChildren().addAll(atributi);
		pozadina.setCenter(boxovi);
		
		Scene scena=new Scene(pozadina);
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
