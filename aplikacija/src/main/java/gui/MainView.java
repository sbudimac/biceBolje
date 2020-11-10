package gui;

import java.util.List;

import api.Entitet;
import api.Skladiste;
import controller.DodajEntitetAction;
import controller.FileConfigAction;
import controller.FiltriranjeEntitetaAction;
import controller.ObrisiEntitetAction;
import controller.UcitajAtributeAction;
import controller.UcitajUSkladisteAction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainView extends Stage {
	private static MainView instance=null;
	private Skladiste skladiste = new Skladiste();

	public Skladiste getSkladiste() {
		return skladiste;
	}

	public void setSkladiste(Skladiste skladiste) {
		this.skladiste = skladiste;
	}

	private Button ucitajUSkladiste;
	private Button ucitajAtribute;
	private Button dodajEntitet;
	private Button pretraziSkladiste;
	private Button obrisiEntitet;
	private Button fileConfig;
	private ToolBar toolbar;
	private HBox meni;
	private TableView<Entitet> tabela;
	
	private MainView() {
		BorderPane pozadina=new BorderPane();
		
		toolbar=new ToolBar();
		
		ucitajUSkladiste=new Button();
		ucitajAtribute=new Button();
		dodajEntitet=new Button();
		pretraziSkladiste=new Button();
		obrisiEntitet=new Button();
		fileConfig=new Button();
		
		Image img = new Image("ikonice/download.png");
		ImageView imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		ucitajUSkladiste.setGraphic(imgView);
		ucitajUSkladiste.setOnAction(new UcitajUSkladisteAction());
		img = new Image("ikonice/attribute.png");
		imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		ucitajAtribute.setGraphic(imgView);
		ucitajAtribute.setOnAction(new UcitajAtributeAction());
		img = new Image("ikonice/add.png");
		imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		dodajEntitet.setGraphic(imgView);
		dodajEntitet.setOnAction(new DodajEntitetAction());
		img = new Image("ikonice/search.png");
		imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		pretraziSkladiste.setGraphic(imgView);
		pretraziSkladiste.setOnAction(new FiltriranjeEntitetaAction());
		img = new Image("ikonice/delete.png");
		imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		obrisiEntitet.setGraphic(imgView);
		obrisiEntitet.setOnAction(new ObrisiEntitetAction());
		img = new Image("ikonice/settings.png");
		imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		fileConfig.setGraphic(imgView);
		fileConfig.setOnAction(new FileConfigAction());
		
		toolbar.getItems().add(ucitajUSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(ucitajAtribute);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(dodajEntitet);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(pretraziSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(obrisiEntitet);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(fileConfig);
		
		meni=new HBox(toolbar);
		meni.setPrefHeight(100);
		pozadina.setTop(meni);
				
		tabela=new TableView<Entitet>();
		tabela.setEditable(true);
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		TableColumn<Entitet, String> colId=new TableColumn<>("Id");
		TableColumn<Entitet, String> colNaziv=new TableColumn<>("Naziv");
		
		tabela.getColumns().add(colId);
		tabela.getColumns().add(colNaziv);
				
		colId.setCellValueFactory(new PropertyValueFactory<Entitet, String>("id"));
		colNaziv.setCellValueFactory(new PropertyValueFactory<Entitet, String>("naziv"));
		
		pozadina.setCenter(tabela);
		
		Scene scena=new Scene(pozadina);
		setScene(scena);
		setWidth(1600);
		setHeight(1000);
		setTitle("Rukovolac entitetima");
		show();
	}
	
	public Button getUcitajAtribute() {
		return ucitajAtribute;
	}

	public Button getDodajEntitet() {
		return dodajEntitet;
	}

	public Button getPretraziSkladiste() {
		return pretraziSkladiste;
	}

	public Button getObrisiEntitet() {
		return obrisiEntitet;
	}

	public Button getFileConfig() {
		return fileConfig;
	}

	public TableView<Entitet> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Entitet> tabela) {
		this.tabela = tabela;
	}
	
	public void popuniTabelu(List<Entitet> entiteti) {
		tabela.getItems().clear();
		tabela.getItems().addAll(entiteti);
	}

	public static MainView getInstance() {
		if(instance==null) {
			instance=new MainView();
		}
		return instance;
	}
}
