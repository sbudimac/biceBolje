package app;

import java.util.ArrayList;
import java.util.List;

import api.AbstractOperator;
import api.Entitet;
import api.Operacija;
import api.Skladiste;
import api.Uslov;
import crudXML.XMLOperator;

public class Main /*extends Application*/ {

	public static void main(String[] args) {
		//launch(args);
		Skladiste.getInstance().setPutanja("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente");
		Entitet gupsi=new Entitet("Gupsi", "ooooo");
		gupsi.dodajAtribut("Indeks", "RN3/18");
		gupsi.dodajAtribut("Smer", "Racunarske nauke");
		Entitet buda=new Entitet("Buda", "meathead");
		buda.dodajAtribut("Indeks", "RN5/18");
		buda.dodajAtribut("Smer", "Racunarske nauke");
		Entitet gale=new Entitet("Gale", "stipendista");
		gale.dodajAtribut("Indeks", "RN2/18");
		gale.dodajAtribut("Smer", "Racunarske nauke");
		Entitet cmoki=new Entitet("Cmoki", "gospoda");
		cmoki.dodajAtribut("Indeks", "RI4/18");
		cmoki.dodajAtribut("Smer", "Racunarsko inzenjerstvo");
		Skladiste.getInstance().dodajEntitet(gupsi);
		Skladiste.getInstance().dodajEntitet(buda);
		Skladiste.getInstance().dodajEntitet(gale);
		Skladiste.getInstance().dodajEntitet(cmoki);
		Uslov uslov=new Uslov("Indeks", Operacija.POCINJE_SA, "RI");
		List<Uslov> uslovi=new ArrayList<>();
		uslovi.add(uslov);
		List<Entitet> rez=Skladiste.getInstance().pretraga(uslovi);
		System.out.println(rez);
		Skladiste.getInstance().brisanje(uslovi);
		AbstractOperator operator=new XMLOperator();
		operator.kreirajSkladiste(Skladiste.getInstance());
	}

	/*@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}*/

}
