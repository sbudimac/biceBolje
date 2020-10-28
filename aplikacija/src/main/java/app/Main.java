package app;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;
import crudJson.JsonOperator;
import crudXML.XMLOperator;

public class Main {

	public static void main(String[] args) {
		Entitet entitet=new Entitet("Gupsi", "ooooo");
		entitet.getAtributi().put("Indeks", "RN6/18");
		entitet.getAtributi().put("Smer", "Racunarske nauke");
		Skladiste.getInstance().dodajEntitet(entitet);
		AbstractOperator operator=new XMLOperator();
		operator.prevodEntiteta(entitet);
	}

}
