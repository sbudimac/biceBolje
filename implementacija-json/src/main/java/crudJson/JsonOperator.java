package crudJson;

import api.AbstractOperator;
import api.Skladiste;
import api.TipFajla;

public class JsonOperator extends AbstractOperator {
	@Override
	public void kreirajSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public Skladiste ucitajSkladiste(String putanja) {
		if(!(proveriTip(putanja)==TipFajla.JSON)) {
			return null;
		}
		return null;
	}

	@Override
	public void izmeniSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void izbrisiSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		return;
	}
}
