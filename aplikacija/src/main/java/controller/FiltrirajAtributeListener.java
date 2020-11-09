package controller;

import java.util.ArrayList;
import java.util.List;

import gui.AttributeDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class FiltrirajAtributeListener implements ChangeListener<String> {

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		List<CheckBox> atributi=AttributeDialog.getAtributi();
		List<String> kljucevi=new ArrayList<>();
		for (CheckBox atribut : atributi) {
			kljucevi.add(atribut.toString());
			if(atribut.getText().startsWith(newValue) || newValue=="") {
				atribut.setVisible(true);
				atribut.setManaged(true);
			}else {
				atribut.setVisible(false);
				atribut.setManaged(false);
			}
		}
		if(newValue!="" && !(kljucevi.contains(newValue))) {
			AttributeDialog.getCustomAtribut().setText(newValue);
			AttributeDialog.getCustomAtribut().setVisible(true);
			AttributeDialog.getCustomAtribut().setManaged(true);
		}
		if(newValue=="") {
			AttributeDialog.getCustomAtribut().setVisible(false);
			AttributeDialog.getCustomAtribut().setManaged(false);
		}
	}

}
