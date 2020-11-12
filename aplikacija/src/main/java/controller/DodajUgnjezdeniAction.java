package controller;

import api.Entitet;
import gui.DodavanjeUgnjezdenogDialog;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class DodajUgnjezdeniAction implements EventHandler<ActionEvent> {
	private DodavanjeUgnjezdenogDialog dialog;
	
	public DodajUgnjezdeniAction(DodavanjeUgnjezdenogDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		String idSpoljasnjeg=dialog.getIds().getSelectionModel().getSelectedItem();
		String ugnjezdeniNaziv=dialog.getNaziv().getText();
		String ugnjezdeniId=dialog.getId().getText();
		Entitet ugnjezden=new Entitet(ugnjezdeniNaziv, ugnjezdeniId);
		String kljucSpoljasnjeg=dialog.getKljuc().getText();
		if(idSpoljasnjeg!=null && idSpoljasnjeg!="" && ugnjezdeniNaziv!=null && ugnjezdeniNaziv!="" && ugnjezdeniId!=null && ugnjezdeniId!="" && kljucSpoljasnjeg!=null && kljucSpoljasnjeg!="") {
			MainView.getInstance().getSkladiste().naknadnoDodaj(idSpoljasnjeg, kljucSpoljasnjeg, ugnjezden);
		}
		Stage stage=(Stage)dialog.getOk().getScene().getWindow();
		stage.close();
	}

}
