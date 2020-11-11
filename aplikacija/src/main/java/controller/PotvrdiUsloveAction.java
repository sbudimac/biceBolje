package controller;

import java.util.Set;
import java.util.function.Predicate;

import api.Entitet;
import gui.FilterDialog;
import gui.FilterGUI;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class PotvrdiUsloveAction implements EventHandler<ActionEvent> {
private FilterDialog dialog;
	
	public PotvrdiUsloveAction(FilterDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		Set<FilterGUI> uslovi=dialog.getRedovi();
		Predicate<Entitet> predikat = null;
		for (FilterGUI uslov : uslovi) {
			if(predikat == null) {
				predikat = uslov.getUslov();
			} else {
				predikat.and(uslov.getUslov());
			}
		}
<<<<<<< HEAD
		entiteti=MainView.getInstance().getSkladiste().pretrazi(filtriranja);
		MainView.getInstance().popuniTabelu(entiteti);
		Stage stage=(Stage)dialog.getOk().getScene().getWindow();
		stage.close();
=======
		MainView.getInstance().getFiltriraniEntiteti().setPredicate(predikat);
		//MainView.getInstance().getSortiraniEntiteti().setPredicate(predikat);
>>>>>>> branch 'master' of https://github.com/sbudimac/biceBolje.git
	}

}
