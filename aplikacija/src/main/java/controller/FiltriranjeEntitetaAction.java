package controller;

import gui.FilterDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FiltriranjeEntitetaAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		FilterDialog dialog=new FilterDialog();
		dialog.showAndWait();
	}

}
