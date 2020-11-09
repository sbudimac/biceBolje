package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import api.Entitet;
import api.FileOperator;
import controller.DodajEntitetAction;
import controller.FileConfigAction;
import controller.UcitajAtributeAction;
import controller.UcitajSkladisteAction;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private FileOperator operator=null;
	
	public FileOperator getOperator() {
		return operator;
	}

	public void setOperator(FileOperator operator) {
		this.operator = operator;
	}

	private Button ucitajSkladiste;
	private Button ucitajAtribute;
	private Button dodajEntitet;
	private Button pretraziSkladiste;
	private Button sortirajSkladiste;
	private Button obrisiEntitet;
	private Button fileConfig;
	private ToolBar toolbar;
	private HBox meni;
	private TableView<Entitet> tabela;
	
	@SuppressWarnings("unchecked")
	private MainView() {
		BorderPane pozadina=new BorderPane();
		
		toolbar=new ToolBar();
		
		ucitajSkladiste=new Button();
		ucitajAtribute=new Button();
		dodajEntitet=new Button();
		pretraziSkladiste=new Button();
		sortirajSkladiste=new Button();
		obrisiEntitet=new Button();
		fileConfig=new Button();
		try {
			FileInputStream input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\download.png");
			Image img=new Image(input);
			ImageView imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			ucitajSkladiste.setGraphic(imgView);
			ucitajSkladiste.setOnAction(new UcitajSkladisteAction());
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\attribute.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			ucitajAtribute.setGraphic(imgView);
			ucitajAtribute.setOnAction(new UcitajAtributeAction());
			if(operator==null) {
				ucitajAtribute.setDisable(true);
			}
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\add.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			dodajEntitet.setGraphic(imgView);
			dodajEntitet.setOnAction(new DodajEntitetAction());
			if(operator==null) {
				dodajEntitet.setDisable(true);
			}
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\search.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			pretraziSkladiste.setGraphic(imgView);
			if(operator==null) {
				pretraziSkladiste.setDisable(true);
			}
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\sort.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			sortirajSkladiste.setGraphic(imgView);
			if(operator==null) {
				sortirajSkladiste.setDisable(true);
			}
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\delete.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			obrisiEntitet.setGraphic(imgView);
			if(operator==null) {
				obrisiEntitet.setDisable(true);
			}
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\settings.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			if(operator==null) {
				fileConfig.setDisable(true);
			}
			fileConfig.setGraphic(imgView);
			fileConfig.setOnAction(new FileConfigAction());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		toolbar.getItems().add(ucitajSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(ucitajAtribute);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(dodajEntitet);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(pretraziSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(sortirajSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(obrisiEntitet);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(fileConfig);
		
		meni=new HBox(toolbar);
		meni.setPrefHeight(100);
		pozadina.setTop(meni);
				
		tabela=new TableView<Entitet>();
		TableColumn<Entitet, String> colId=new TableColumn<>("Id");
		TableColumn<Entitet, String> colNaziv=new TableColumn<>("Naziv");
		
		tabela.getColumns().addAll(colId, colNaziv);
		
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

	public Button getSortirajSkladiste() {
		return sortirajSkladiste;
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
