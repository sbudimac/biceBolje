package controller;


import gui.AttributeDialog;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UcitajAtributeAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		AttributeDialog dialog=new AttributeDialog(MainView.getInstance().getSkladiste().getKljucevi());
		dialog.showAndWait();
	}

}
