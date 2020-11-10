package controller;


import gui.FilterDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DodajUslovAction implements EventHandler<ActionEvent> {
private FilterDialog dialog;
	
	public DodajUslovAction(FilterDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		dialog.noviUslov();
	}

}
