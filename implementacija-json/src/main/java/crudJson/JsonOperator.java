package crudJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;
import api.Uslov;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonOperator extends AbstractOperator {
	public JsonOperator(String putanja) {
		super(putanja);
	}

	@Override
	public void kreirajSkladiste(Skladiste skladiste) {
		for (String fajl : skladiste.getFajloviEntiteta().keySet()) {
			List<Entitet> entiteti=skladiste.getForFile(fajl);
			try {
				Writer writer=new FileWriter(new File(fajl), false);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(entiteti, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public Skladiste ucitajSkladiste(String putanja) {
		skladiste=new Skladiste(putanja);
		File direktorijum=new File(putanja);
		File[] fajloviDirektorijuma=direktorijum.listFiles();
		Gson gson=new Gson();
		if(fajloviDirektorijuma!=null) {
			for (File fajl : fajloviDirektorijuma) {
				if(validanFajl(fajl.toString())) {
					try {
						Reader reader = Files.newBufferedReader(Paths.get(fajl.toString()));
						List<Entitet> entiteti = gson.fromJson(reader, new TypeToken<List<Entitet>>() {}.getType());
						skladiste.nalepiEntitete(entiteti, putanja);
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return skladiste;
	}

	@Override
	public void prevediEntitet(Entitet entitet) {
		Gson gson=new Gson();
		System.out.println(gson.toJson(entitet));
	}

	@Override
	protected boolean validanFajl(String putanja) {
		File fajl=new File(putanja);
		try {
			Scanner reader=new Scanner(fajl);
			String fileStart=reader.nextLine();
			if(fileStart.charAt(0)=='{' || fileStart.charAt(0)=='[') {
				reader.close();
				return true;
			}else {
				reader.close();
				return false;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void dodajEntitet(Entitet entitet) {
		String fajl=skladiste.dodajEntitet(entitet);
		if(fajl==null) {
			return;
		}
		List<Entitet> entiteti=skladiste.getForFile(fajl);
		try {
			Writer writer=new FileWriter(new File(fajl), false);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(entiteti, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void izmeniEntitet(Entitet entitet, String kljuc, String vrednost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brisi(List<Uslov> uslovi) {
		Set<String> fajlovi=skladiste.brisi(uslovi);
		for (String fajl : fajlovi) {
			if(fajl==null) {
				return;
			}
			List<Entitet> entiteti=skladiste.getForFile(fajl);
			try {
				Writer writer=new FileWriter(new File(fajl), false);
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				gson.toJson(entiteti, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
