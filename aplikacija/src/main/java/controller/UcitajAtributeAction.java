package controller;

import java.util.Set;

import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UcitajAtributeAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		MainView view=MainView.getInstance();
		Set<String> atributi=view.getOperator().getKljucevi();
		
	}

}
