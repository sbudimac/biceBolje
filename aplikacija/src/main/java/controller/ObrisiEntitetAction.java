package controller;

import java.util.List;

import api.Entitet;
import gui.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

public class ObrisiEntitetAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		TableView<Entitet> tabela=MainView.getInstance().getTabela();
		List<Entitet> entiteti=tabela.getSelectionModel().getSelectedItems();
		for (Entitet entitet : entiteti) {
			MainView.getInstance().getSkladiste().brisi(entitet);
		}
		MainView.getInstance().popuniTabelu(MainView.getInstance().getSkladiste().getEntiteti());
	}

}
