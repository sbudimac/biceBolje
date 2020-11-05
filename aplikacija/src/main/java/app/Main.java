package app;

import java.util.ArrayList;
import java.util.List;

import api.AbstractOperator;
import api.Entitet;
import api.Operacija;
import api.Skladiste;
import api.Uslov;
import api.UslovFactory;
import crudXML.XMLOperator;

public class Main /*extends Application*/ {

	public static void main(String[] args) {
		//launch(args);
		Skladiste.getInstance().setPutanja("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente");
		Entitet gupsi=new Entitet("Gupsi", "ooooo");
		gupsi.dodajAtribut("Indeks", "RN3/18");
		gupsi.dodajAtribut("Smer", "Racunarske nauke");
		gupsi.dodajAtribut("Ocena", 5);
		Entitet buda=new Entitet("Buda", "meathead");
		buda.dodajAtribut("Indeks", "RN5/18");
		buda.dodajAtribut("Smer", "Racunarske nauke");
		buda.dodajAtribut("Ocena", 9);
		Entitet gale=new Entitet("Gale", "stipendista");
		gale.dodajAtribut("Indeks", "RN2/18");
		gale.dodajAtribut("Smer", "Racunarske nauke");
		gale.dodajAtribut("Ocena", 10);
		Entitet cmoki=new Entitet("Cmoki", "gospoda");
		cmoki.dodajAtribut("Indeks", "RI4/18");
		cmoki.dodajAtribut("Smer", "Racunarsko inzenjerstvo");
		cmoki.dodajAtribut("Ocena", 6);
		Skladiste.getInstance().dodajEntitet(gupsi);
		Skladiste.getInstance().dodajEntitet(buda);
		Skladiste.getInstance().dodajEntitet(gale);
		Skladiste.getInstance().dodajEntitet(cmoki);
		//Uslov uslov=n("Indeks", Operacija.POCINJE_SA, "RI");
		
		Uslov uslov = UslovFactory.KreirajUslov("Indeks", Operacija.POCINJE_SA, "RI");
		List<Uslov> uslovi=new ArrayList<>();
		
		uslovi.add(uslov);
		List<Entitet> rez=Skladiste.getInstance().pretrazi(uslovi);
		
		uslovi.clear();
		Uslov uslov2 = UslovFactory.KreirajUslov("Ocena", Operacija.VECE_JEDNAKO, "6");
		uslovi.add(uslov2);
		rez=Skladiste.getInstance().pretrazi(uslovi);
		List<String> kriterijumi = new ArrayList<>();
		kriterijumi.add("Ocena");
		Skladiste.getInstance().sortiraj(kriterijumi, rez);
		for(Entitet e : rez) {
			System.out.println(e.getId());
		}
		
		Skladiste.getInstance().brisi(uslovi);
		AbstractOperator operator=new XMLOperator();
		operator.kreirajSkladiste(Skladiste.getInstance());
	}

	/*@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}*/

}
