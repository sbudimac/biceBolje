package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AttributeDialog extends Stage {
	private static AttributeDialog instance=null;
	
	BorderPane pozadina=new BorderPane();
	
	private TextField pretrazivac;
	private List<CheckBox> atributi;
	
	private AttributeDialog() {
		pretrazivac=new TextField();
		
		atributi=new ArrayList<CheckBox>();
		Set<String> kljucevi=MainView.getInstance().getOperator().getKljucevi();
	}

	public static AttributeDialog getInstance() {
		if(instance==null) {
			instance=new AttributeDialog();
		}
		return instance;
	}
}
