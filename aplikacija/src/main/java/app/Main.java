package app;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;
import crudXML.XMLOperator;

public class Main /*extends Application*/ {

	public static void main(String[] args) {
		//launch(args);
		Skladiste.getInstance().setPutanja("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente");
		Entitet gupsi=new Entitet("Gupsi", "ooooo");
		gupsi.getAtributi().put("Indeks", "RN3/18");
		gupsi.getAtributi().put("Smer", "Racunarske nauke");
		Entitet buda=new Entitet("Buda", "meathead");
		buda.getAtributi().put("Indeks", "RN5/18");
		buda.getAtributi().put("Smer", "Racunarske nauke");
		Entitet gale=new Entitet("Gale", "stipendista");
		gale.getAtributi().put("Indeks", "RN2/18");
		gale.getAtributi().put("Smer", "Racunarske nauke");
		Entitet cmoki=new Entitet("Cmoki", "gospoda");
		cmoki.getAtributi().put("Indeks", "RN4/18");
		cmoki.getAtributi().put("Smer", "Racunarske nauke");
		Skladiste.getInstance().dodajEntitet(gupsi);
		Skladiste.getInstance().dodajEntitet(buda);
		Skladiste.getInstance().dodajEntitet(gale);
		Skladiste.getInstance().dodajEntitet(cmoki);
		AbstractOperator operator=new XMLOperator();
		operator.kreirajSkladiste(Skladiste.getInstance());
	}

	/*@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}*/

}
