package controller;

import api.Entitet;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class DodavanjeEntitetaAction implements EventHandler<ActionEvent> {
	private TextField naziv;
	private TextField id;
	
	public DodavanjeEntitetaAction(TextField naziv, TextField id) {
		this.naziv=naziv;
		this.id=id;
	}

	@Override
	public void handle(ActionEvent event) {
		if(naziv!=null && id!=null && naziv.getText()!="" && id.getText()!="") {
			Entitet entitet=new Entitet(naziv.getText(), id.getText());
			MainView.getInstance().getSkladiste().dodajEntitet(entitet);
		}
	}

}
