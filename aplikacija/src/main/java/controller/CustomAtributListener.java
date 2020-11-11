package controller;

import gui.AttributeDialog;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

public class CustomAtributListener implements ChangeListener<Boolean> {
	private AttributeDialog dialog;
	private CheckBox cb;
	
	public CustomAtributListener(AttributeDialog dialog, CheckBox cb) {
		this.dialog = dialog;
		this.cb = cb;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if(newValue) {
			dialog.getAtributi().add(cb);
			dialog.dodajNoviAtribut();
		} else {
			dialog.getAtributi().remove(cb);
			dialog.getBoxovi().getChildren().remove(cb);
		}
	}

}
