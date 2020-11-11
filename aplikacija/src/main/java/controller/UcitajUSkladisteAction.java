package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import crudCustom.CustomOperator;
import crudJson.JsonOperator;
import crudYaml.YamlOperator;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.DirectoryChooser;

public class UcitajUSkladisteAction implements EventHandler<ActionEvent> {
	
	@Override
	public void handle(ActionEvent event) {
		MainView view=MainView.getInstance();
		List<String> choices = new ArrayList<>();
		choices.add("Json");
		choices.add("Yaml");
		choices.add("Custom");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("Json", choices);
		dialog.setTitle("Operatori");
		dialog.setHeaderText("Odabir operatora fajlova");
		dialog.setContentText("Odaberite format fajlova:");
		
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			DirectoryChooser dirChooser=new DirectoryChooser();
			File selectedFile=dirChooser.showDialog(view);
			if(selectedFile!=null) {
				if(result.get().equals("Json")) {
					view.getSkladiste().setOperator(new JsonOperator(selectedFile.getAbsolutePath()));
				}else if(result.get().equals("Yaml")) {
					view.getSkladiste().setOperator(new YamlOperator(selectedFile.getAbsolutePath()));
				}else if(result.get().equals("Custom")) {
					view.getSkladiste().setOperator(new CustomOperator(selectedFile.getAbsolutePath()));
				}
			}
		}
		view.getFileConfig().setDisable(false);
	}

}
