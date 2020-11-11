package controller;

import api.Entitet;
import gui.DodavanjeEntitetaDialog;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DodavanjeEntitetaAction implements EventHandler<ActionEvent> {
	private TextField naziv;
	private TextField id;
	private DodavanjeEntitetaDialog dialog;
	
	public DodavanjeEntitetaAction(TextField naziv, TextField id, DodavanjeEntitetaDialog dialog) {
		this.naziv=naziv;
		this.id=id;
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		if(naziv!=null && id!=null && naziv.getText()!="" && id.getText()!="") {
			Entitet entitet=new Entitet(naziv.getText(), id.getText());
			MainView.getInstance().getSkladiste().dodajEntitet(entitet);
		}
		Stage stage=(Stage)dialog.getOk().getScene().getWindow();
		stage.close();
	}

}
