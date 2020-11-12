package controller;

import java.util.Optional;
import java.util.Random;

import gui.DodavanjeEntitetaDialog;
import gui.DodavanjeUgnjezdenogDialog;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class DodajEntitetAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Odabir nacina dodeljivanja Id-ja entitetima");
		alert.setHeaderText("Morate odabrati nacin na koji ce se Id entiteta dodeliti");
		alert.setContentText("Odaberite jedno od ponudjenog.");

		ButtonType automatski = new ButtonType("Automatski");
		ButtonType rucno = new ButtonType("Rucno");
		ButtonType ugnjezden = new ButtonType("Ugnjezden");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(automatski, rucno, ugnjezden, cancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == automatski){
			DodavanjeEntitetaDialog dialog=new DodavanjeEntitetaDialog(generateId());
			dialog.showAndWait();
		} else if (result.get() == rucno) {
			DodavanjeEntitetaDialog dialog=new DodavanjeEntitetaDialog();
			dialog.showAndWait();
		} else if(result.get() == ugnjezden){
		    DodavanjeUgnjezdenogDialog dialog=new DodavanjeUgnjezdenogDialog();
		    dialog.showAndWait();
		}
	}
	
	private String generateId() {
		Random r=new Random();
		int left=97;
		int right=122;
		int len=r.nextInt((16-4)+1)+4;
		String id=r.ints(left, right+1).limit(len).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return id;
	}

}
