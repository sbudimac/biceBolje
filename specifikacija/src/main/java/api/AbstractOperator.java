package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractOperator {
	public abstract void kreirajSkladiste(Skladiste skladiste);
	
	public abstract Skladiste ucitajSkladiste(String putanja);
	
	public abstract void izmeniSkladiste(String putanja, Skladiste skladiste);
	
	public abstract void izbrisiSkladiste(String putanja, Skladiste skladiste);
	
	public abstract void prevediEntitet(Entitet entitet);
	
	public TipFajla proveriTip(String putanja) {
		File file=new File(putanja);
		TipFajla tip=TipFajla.INVALID;
		try {
			Scanner reader=new Scanner(file);
			String fileStart=reader.nextLine();
			if(fileStart.charAt(0)=='{') {
				tip=TipFajla.JSON;
			}else if (fileStart.charAt(0)=='<') {
				tip=TipFajla.XML;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tip;
	}
}
