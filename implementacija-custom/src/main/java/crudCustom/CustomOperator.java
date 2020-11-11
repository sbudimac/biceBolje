package crudCustom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import api.Entitet;
import api.FileOperator;

public class CustomOperator extends FileOperator {

	public CustomOperator(String putanja) {
		super(putanja);
	}

	@Override
	protected List<Entitet> prevediFajl(File fajl) {
		List<Entitet> entiteti=new ArrayList<>();
		if(validanFajl(fajl)) {
			try {
				BufferedReader reader=new BufferedReader(new FileReader(fajl));
				String linija;
				while((linija=reader.readLine())!=null) {
					String[] atributi=linija.split(";");
					for(int i=0; i<atributi.length; i++) {
						String naziv="", id="";
						if(atributi[i].contains("Naziv")) {
							String[] entitetNaziv=atributi[i].split(":");
							naziv=entitetNaziv[1];
						}else if(atributi[i].contains("Id")) {
							String[] entitetId=atributi[i].split(":");
							id=entitetId[1];
						}
						Entitet entitet=new Entitet(naziv, id);
						entiteti.add(entitet);
						i++;
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String prevediEntitet(Entitet entitet) {
		return entitet.toString();
	}

	@Override
	protected void ispisiFajl(Path putanjaFajla) {
		List<Entitet> entiteti=fajloviEntiteta.get(putanjaFajla);
		try {
			Writer writer=new FileWriter(putanja.resolve(putanjaFajla).toFile(), false);
			for (Entitet entitet : entiteti) {
				writer.write(entitet.toString());
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validanFajl(File fajl) {
		try {
			Scanner reader=new Scanner(fajl);
			String fileStart=reader.nextLine();
			if(fileStart.charAt(0)=='N') {
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

}
