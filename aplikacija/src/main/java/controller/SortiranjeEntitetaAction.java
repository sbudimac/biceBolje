package controller;

import gui.SortDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SortiranjeEntitetaAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		SortDialog dialog=new SortDialog();
		dialog.show();
	}

}
