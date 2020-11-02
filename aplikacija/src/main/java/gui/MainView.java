package gui;


import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Stage {
	private static MainView instance=null;
	
	private MainView() {
		VBox pozadina=new VBox();
		Scene scena=new Scene(pozadina);
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
