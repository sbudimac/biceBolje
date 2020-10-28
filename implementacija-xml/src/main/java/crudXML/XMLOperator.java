package crudXML;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;

public class XMLOperator extends AbstractOperator {

	@Override
	public void kreirajSkladiste(String putanja, Skladiste skladiste) {
		// TODO Auto-generated method stub
		
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
	public void prevodEntiteta(Entitet entitet) {
		XmlMapper xmlMapper=new XmlMapper();
		try {
			System.out.println(xmlMapper.writeValueAsString(entitet));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
