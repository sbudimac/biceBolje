package controller;

import java.util.List;

import api.Entitet;
import gui.AttributeDialog;
import gui.MainView;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DodajKoloneAction implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		TableView<Entitet> tabela=MainView.getInstance().getTabela();
		List<CheckBox> atributi=AttributeDialog.getAtributi();
		tabela.getColumns().clear();
		TableColumn<Entitet, String> colId=new TableColumn<>("Id");
		TableColumn<Entitet, String> colNaziv=new TableColumn<>("Naziv");
		
		tabela.getColumns().add(colId);
		tabela.getColumns().add(colNaziv);
		
		colId.setCellValueFactory(new PropertyValueFactory<Entitet, String>("id"));
		colNaziv.setCellValueFactory(new PropertyValueFactory<Entitet, String>("naziv"));
		for (CheckBox atribut : atributi) {
			if(atribut.isSelected()) {
				TableColumn<Entitet, String> column=new TableColumn<>(atribut.getText());
				column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Entitet,String>, ObservableValue<String>>() {
					
					@Override
					public ObservableValue<String> call(CellDataFeatures<Entitet, String> param) {
						Object polje=param.getValue().getAtribut(atribut.getText());
						String vrednost="";
						if(polje!=null) {
							vrednost=polje.toString();
						}
						return new SimpleStringProperty(vrednost);
					}
				});
				tabela.getColumns().add(column);
			}
		}
	}

}
