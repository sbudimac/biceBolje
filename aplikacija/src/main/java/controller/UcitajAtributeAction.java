package controller;


import gui.AttributeDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UcitajAtributeAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		AttributeDialog dialog=new AttributeDialog();
		dialog.showAndWait();
	}

}
