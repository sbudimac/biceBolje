package controller;

import java.util.Optional;

import api.FileOperator;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;

public class FileConfigAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Ogranicavanje fajlova");
		dialog.setHeaderText("Unesite ogranicenje za fajlove");
		dialog.setContentText("Unesite maksimalan broj entiteta po fajlu:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			FileOperator operator=(FileOperator) MainView.getInstance().getSkladiste().getOperator();
			if(operator!=null) {
				operator.setKonfiguracija(Integer.parseInt(result.get()));
			}
		}
	}
	
}
