package gui;

import api.Entitet;
import api.Skladiste;
import controller.DodajEntitetAction;
import controller.FileConfigAction;
import controller.FiltriranjeEntitetaAction;
import controller.ObrisiEntitetAction;
import controller.UcitajAtributeAction;
import controller.UcitajUSkladisteAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private FilteredList<Entitet> filtriraniEntiteti;

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
		
		ImageView imgView = loadImage(this.getClass().getResource(("/ikonice/download.png")).toString());
		ucitajUSkladiste.setGraphic(imgView);
		ucitajUSkladiste.setOnAction(new UcitajUSkladisteAction());
		imgView = loadImage(this.getClass().getResource(("/ikonice/attribute.png")).toString());
		ucitajAtribute.setGraphic(imgView);
		ucitajAtribute.setOnAction(new UcitajAtributeAction());
		imgView = loadImage(this.getClass().getResource(("/ikonice/add.png")).toString());
		dodajEntitet.setGraphic(imgView);
		dodajEntitet.setOnAction(new DodajEntitetAction());
		imgView = loadImage(this.getClass().getResource(("/ikonice/search.png")).toString());
		pretraziSkladiste.setGraphic(imgView);
		pretraziSkladiste.setOnAction(new FiltriranjeEntitetaAction());
		imgView = loadImage(this.getClass().getResource(("/ikonice/delete.png")).toString());
		obrisiEntitet.setGraphic(imgView);
		obrisiEntitet.setOnAction(new ObrisiEntitetAction());
		imgView = loadImage(this.getClass().getResource(("/ikonice/settings.png")).toString());
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
		ObservableList<Entitet> entiteti = FXCollections.observableList(skladiste.getEntiteti());
		skladiste.setEntiteti(entiteti);
		filtriraniEntiteti = new FilteredList<Entitet>(entiteti, p -> true);
		SortedList<Entitet> sortiraniEntiteti = new SortedList<Entitet>(filtriraniEntiteti);
		sortiraniEntiteti.comparatorProperty().bind(tabela.comparatorProperty());
		
		tabela.setItems(sortiraniEntiteti);
		
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
	
	private ImageView loadImage(String imgPath) {
		Image img = new Image(imgPath);
		ImageView imgView=new ImageView(img);
		imgView.setFitHeight(30);
		imgView.setFitWidth(30);
		return imgView;
	}
	
	public Button getFileConfig() {
		return fileConfig;
	}

	public TableView<Entitet> getTabela() {
		return tabela;
	}

	public FilteredList<Entitet> getFiltriraniEntiteti() {
		return filtriraniEntiteti;
	}

	public Skladiste getSkladiste() {
		return skladiste;
	}

	public static MainView getInstance() {
		if(instance==null) {
			instance=new MainView();
		}
		return instance;
	}
}
