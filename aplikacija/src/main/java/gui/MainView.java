package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import api.Entitet;
import api.FileOperator;
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
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\add.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			dodajEntitet.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\search.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			pretraziSkladiste.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\sort.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			sortirajSkladiste.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\delete.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			obrisiEntitet.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\settings.png");
			img=new Image(input);
			imgView=new ImageView(img);
			imgView.setFitHeight(30);
			imgView.setFitWidth(30);
			fileConfig.setGraphic(imgView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*ImageView imgView=new ImageView("ikonice\file.png");
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
		ucitajSkladiste=new Button("ucitaj", imgView);
		imgView=new ImageView("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\search.png");
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
		pretraziSkladiste=new Button("pretrazi", imgView);
		imgView=new ImageView("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\sort.png");
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
		sortirajSkladiste=new Button("sortiraj", imgView);*/
		
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
