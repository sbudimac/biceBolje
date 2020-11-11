package controller;

import java.util.List;
import java.util.Set;

import gui.AttributeDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class FiltrirajAtributeListener implements ChangeListener<String> {
	private AttributeDialog dialog;
	private Set<String> kljucevi;
	
	public FiltrirajAtributeListener(AttributeDialog dialog, Set<String> kljucevi) {
		this.dialog=dialog;
		this.kljucevi = kljucevi;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		List<CheckBox> atributi=dialog.getAtributi();
		for (CheckBox atribut : atributi) {
			if(atribut.getText().startsWith(newValue) || newValue.equals("")) {
				atribut.setVisible(true);
				atribut.setManaged(true);
			}else {
				atribut.setVisible(false);
				atribut.setManaged(false);
			}
		}
		if(!newValue.equals("") && !kljucevi.contains(newValue)) {
			dialog.getCustomAtribut().setText(newValue);
			dialog.getCustomAtribut().setVisible(true);
			dialog.getCustomAtribut().setManaged(true);
		}else {
			dialog.getCustomAtribut().setVisible(false);
			dialog.getCustomAtribut().setManaged(false);
		}
	}

}
