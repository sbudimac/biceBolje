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
			operator.setKonfiguracija(Integer.parseInt(result.get()));
		}
		result.ifPresent(name -> System.out.println("Your name: " + name));
	}
	
}
