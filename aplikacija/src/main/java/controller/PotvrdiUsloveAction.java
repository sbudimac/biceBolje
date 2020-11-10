package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import api.Entitet;
import api.Uslov;
import gui.FilterDialog;
import gui.FilterGUI;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PotvrdiUsloveAction implements EventHandler<ActionEvent> {
private FilterDialog dialog;
	
	public PotvrdiUsloveAction(FilterDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		Set<FilterGUI> uslovi=dialog.getRedovi();
		List<Uslov> filtriranja=new ArrayList<>();
		List<Entitet> entiteti;
		for (FilterGUI uslov : uslovi) {
			filtriranja.add(uslov.getUslov());
		}
		entiteti=MainView.getInstance().getSkladiste().pretrazi(filtriranja);
		MainView.getInstance().popuniTabelu(entiteti);
	}

}
