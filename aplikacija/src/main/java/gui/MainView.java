package gui;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Stage {
	private static MainView instance=null;
	
	private MainView() {
		BorderPane pozadina=new BorderPane();
		
		ToolBar toolbar=new ToolBar();
		Button ucitajSkladiste=new Button();
		Button pretraziSkladiste=new Button();
		Button sortirajSkladiste=new Button();
		try {
			FileInputStream input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\file.png");
			Image img=new Image(input);
			ImageView imgView=new ImageView(img);
			ucitajSkladiste.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\search.png");
			img=new Image(input);
			imgView=new ImageView(img);
			pretraziSkladiste.setGraphic(imgView);
			input=new FileInputStream("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\biceBolje\\aplikacija\\ikonice\\sort.png");
			img=new Image(input);
			imgView=new ImageView(img);
			sortirajSkladiste.setGraphic(imgView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ucitajSkladiste.getStyleClass().add("toolbar-button");
		pretraziSkladiste.getStyleClass().add("toolbar-button");
		sortirajSkladiste.getStyleClass().add("toolbar-button");
		
		toolbar.getItems().add(ucitajSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(pretraziSkladiste);
		toolbar.getItems().add(new Separator());
		toolbar.getItems().add(sortirajSkladiste);
		
		HBox meni=new HBox(toolbar);
		meni.setMaxHeight(50);
		pozadina.setTop(meni);
		
		Scene scena=new Scene(pozadina);
		String cssPutanja=new String("/aplikacija/dizajn.css");
		scena.getStylesheets().add(getClass().getResource(cssPutanja).toExternalForm());
		setScene(scena);
		setWidth(1000);
		setHeight(700);
		setTitle("Rukovolac entitetima");
		show();
	}
	
	public static MainView getInstance() {
		if(instance==null) {
			instance=new MainView();
		}
		return instance;
	}
}
