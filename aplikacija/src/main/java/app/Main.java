package app;

import java.util.ArrayList;
import java.util.List;

import api.AbstractOperator;
import api.Entitet;
import api.Operacija;
import api.Skladiste;
import api.Uslov;
import api.UslovFactory;
import crudJson.JsonOperator;
import crudYaml.YamlOperator;

public class Main /*extends Application*/ {

	public static void main(String[] args) {
		//launch(args);
		Skladiste skladiste=new Skladiste("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente");
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
		
		
		AbstractOperator operator=new YamlOperator("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente\\testovi");
		//AbstractOperator operator=new YamlOperator("C:\\Users\\Stefan\\Desktop\\RAF\\5. semestar\\Softverske komponente");
		
		//operator.prevediEntitet(cmoki);
		//operator.kreirajSkladiste(Skladiste.getInstance());
		
		/*
		operator.dodajEntitet(gupsi);
		operator.dodajEntitet(buda);
		operator.dodajEntitet(gale);
		operator.dodajEntitet(cmoki);
		*/
		
		for (Entitet entitet : operator.getEntiteti()) {
			operator.prevediEntitet(entitet);
		}
		
		Uslov uslov = UslovFactory.KreirajUslov("Indeks", Operacija.POCINJE_SA, "RI");
		List<Uslov> uslovi=new ArrayList<>();
		
		uslovi.add(uslov);
		List<Entitet> rez=operator.pretrazi(uslovi);
		
		uslovi.clear();
		Uslov uslov2 = UslovFactory.KreirajUslov("Ocena", Operacija.JEDNAKO, "5");
		uslovi.add(uslov2);
		rez=operator.pretrazi(uslovi);
		List<String> kriterijumi = new ArrayList<>();
		kriterijumi.add("Ocena");
		operator.sortiraj(kriterijumi, rez);
		for(Entitet e : rez) {
			System.out.println(e.getId());
		}
		operator.brisi(uslovi);
	}

	/*@Override
	public void start(Stage primaryStage) throws Exception {
		MainView.getInstance();
	}*/

}
