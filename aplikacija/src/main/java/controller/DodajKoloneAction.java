package controller;

import java.util.List;

import api.AttributeComparator;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DodajKoloneAction implements EventHandler<ActionEvent> {
	private AttributeDialog dialog;
	
	public DodajKoloneAction(AttributeDialog dialog) {
		this.dialog=dialog;
	}

	@Override
	public void handle(ActionEvent event) {
		TableView<Entitet> tabela=MainView.getInstance().getTabela();
		List<CheckBox> atributi=dialog.getAtributi();
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
				column.setCellFactory(TextFieldTableCell.forTableColumn());
				column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Entitet,String>>() {
					
					@Override
					public void handle(CellEditEvent<Entitet, String> event) {
						MainView.getInstance().getSkladiste().izmeniEntitet(event.getRowValue(), atribut.getText(), event.getNewValue());
					}
				});
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
				column.setSortable(true);
				column.setComparator(new AttributeComparator());
				tabela.getColumns().add(column);
			}
		}
		Stage stage=(Stage)dialog.getOk().getScene().getWindow();
		stage.close();
	}

}
