package crudJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import api.Entitet;
import api.FileOperator;

import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonOperator extends FileOperator {
	private Gson gson=new GsonBuilder().setPrettyPrinting().create();
	
	public JsonOperator(String putanja) {
		super(putanja);
		ucitajSkladiste();
	}

	@Override
	public void prevediEntitet(Entitet entitet) {
		System.out.println(gson.toJson(entitet));
	}

	@Override
	protected boolean validanFajl(Path putanjaFajla) {
		File fajl=putanjaFajla.toFile();
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
	protected List<Entitet> prevediFajl(File fajl) {
		if(validanFajl(fajl.toPath())) {
			try {
				Reader reader = Files.newBufferedReader(fajl.toPath());
				List<Entitet> entiteti = gson.fromJson(reader, new TypeToken<List<Entitet>>() {}.getType());
				reader.close();
				return entiteti;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void ispisiFajl(Path putanjaFajla) {
		try {
			Writer writer=new FileWriter(putanja.resolve(putanjaFajla).toFile(), false);
			gson.toJson(fajloviEntiteta.get(putanjaFajla), writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
