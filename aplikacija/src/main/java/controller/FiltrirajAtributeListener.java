package controller;

import java.util.ArrayList;
import java.util.List;

import gui.AttributeDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class FiltrirajAtributeListener implements ChangeListener<String> {
	private AttributeDialog dialog;
	
	public FiltrirajAtributeListener(AttributeDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		List<CheckBox> atributi=dialog.getAtributi();
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
			dialog.getCustomAtribut().setText(newValue);
			dialog.getCustomAtribut().setVisible(true);
			dialog.getCustomAtribut().setManaged(true);
		}else if(newValue=="") {
			dialog.getCustomAtribut().setVisible(false);
			dialog.getCustomAtribut().setManaged(false);
		}
	}

}
