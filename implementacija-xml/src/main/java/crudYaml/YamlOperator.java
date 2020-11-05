package crudYaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import api.AbstractOperator;
import api.Entitet;
import api.Skladiste;
import api.Uslov;

public class YamlOperator extends AbstractOperator {

	public YamlOperator(String putanja) {
		super(putanja);
	}

	@Override
	public void kreirajSkladiste(Skladiste skladiste) {
		for(String fajl : skladiste.getFajloviEntiteta().keySet()) {
			List<Entitet> entiteti=skladiste.getForFile(fajl);
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			try {
				om.writeValue(new File(fajl), entiteti);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
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
		if(fajloviDirektorijuma!=null) {
			for (File fajl : fajloviDirektorijuma) {
				if(validanFajl(fajl.toString())) {
					ObjectMapper om = new ObjectMapper(new YAMLFactory());
					try {
						List<Entitet> entiteti = om.readValue(fajl, new TypeReference<List<Entitet>>() {});
						skladiste.nalepiEntitete(entiteti, putanja);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
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
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		try {
			om.writeValue(System.out, entitet);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validanFajl(String putanja) {
		File fajl=new File(putanja);
		try {
			Scanner reader=new Scanner(fajl);
			String fileStart=reader.nextLine();
			if(fileStart.charAt(0)=='-') {
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
		ObjectMapper om = new ObjectMapper(new YAMLFactory());
		try {
			om.writeValue(new File(fajl), entiteti);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
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
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			try {
				om.writeValue(new File(fajl), entiteti);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
