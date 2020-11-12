package app;

import api.Entitet;
import api.FileOperator;
import api.Skladiste;
import crudCustom.CustomOperator;
import gui.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
		
		/*Skladiste skladiste = new Skladiste();
		skladiste.setOperator(new CustomOperator("C:\\Users\\andro\\OneDrive\\Desktop\\testiranjeCUSTOM"));
        Entitet gupsi=new Entitet("Gupsi", "ooooo");
        gupsi.setAtribut("Indeks", "RN3/18");
        gupsi.setAtribut("Smer", "Racunarske nauke");
        gupsi.setAtribut("Ocena", "5");
        Entitet buda=new Entitet("Buda", "meathead");
        buda.setAtribut("Indeks", "RN5/18");
        buda.setAtribut("Smer", "Racunarske nauke");
        buda.setAtribut("Ocena", "9");
        Entitet gale=new Entitet("Gale", "stipendista");
        gale.setAtribut("Indeks", "RN2/18");
        gale.setAtribut("Smer", "Racunarske nauke");
        gale.setAtribut("Ocena", "10");
        Entitet cmoki=new Entitet("Cmoki", "gospoda");
        cmoki.dodajUgnjezdeni("tim1", gupsi);
        cmoki.dodajUgnjezdeni("tim2", buda);
        cmoki.setAtribut("Indeks", "RI4/18");
        cmoki.setAtribut("Smer", "Racunarsko inzenjerstvo");
        cmoki.setAtribut("Ocena", "6");

        skladiste.dodajEntitet(gale);
        skladiste.dodajEntitet(cmoki);
		
		FileOperator fileOperator = (FileOperator)skladiste.getOperator();
		for(Entitet e : skladiste.getEntiteti()) {
			System.out.println(fileOperator.prevediEntitet(e));
		}*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}
}
