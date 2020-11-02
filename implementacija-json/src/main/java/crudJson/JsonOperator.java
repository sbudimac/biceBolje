package crudJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;
import api.TipFajla;

import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JsonOperator extends AbstractOperator {
	@Override
	public void kreirajSkladiste(Skladiste skladiste) {
		for (Map.Entry<String, ArrayList<Entitet>> grupaEntiteta : skladiste.getFajloviEntiteta().entrySet()) {
			try {
				Writer writer=new FileWriter(new File(grupaEntiteta.getKey()));
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(grupaEntiteta.getValue(), writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	
	@Override
	public void prevediEntitet(Entitet entitet) {
		Gson gson=new Gson();
		System.out.println(gson.toJson(entitet));
	}
}
