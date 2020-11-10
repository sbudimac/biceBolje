package crudYaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import api.Entitet;
import api.FileOperator;

public class YamlOperator extends FileOperator {
	private ObjectMapper om = new ObjectMapper(new YAMLFactory());
	
	public YamlOperator(String putanja) {
		super(putanja);
	}

	@Override
	public void prevediEntitet(Entitet entitet) {
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
	protected boolean validanFajl(Path putanjaFajla) {
		File fajl=putanjaFajla.toFile();
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
	protected List<Entitet> prevediFajl(File fajl) {
		try {
			List<Entitet> entiteti = om.readValue(fajl, new TypeReference<List<Entitet>>() {});
			return entiteti;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void ispisiFajl(Path putanjaFajla) {
		try {
			om.writeValue(putanja.resolve(putanjaFajla).toFile(), fajloviEntiteta.get(putanjaFajla));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
