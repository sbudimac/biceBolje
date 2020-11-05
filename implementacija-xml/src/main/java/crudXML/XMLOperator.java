package crudXML;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;

public class XMLOperator extends AbstractOperator {

	@Override
	public void kreirajSkladiste(Skladiste skladiste) {
		for(String fajl : skladiste.getFajloviEntiteta().keySet()) {
			List<Entitet> entiteti=skladiste.getForFile(fajl);
			try {
				XmlMapper xmlMapper=new XmlMapper();
				xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
				xmlMapper.writeValue(new File(fajl), entiteti);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@Override
	public Skladiste ucitajSkladiste(String putanja) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void izmeniSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void izbrisiSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prevediEntitet(Entitet entitet) {
		XmlMapper xmlMapper=new XmlMapper();
		try {
			System.out.println(xmlMapper.writeValueAsString(entitet));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
