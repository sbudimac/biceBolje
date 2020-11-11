package api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Skladiste {	
	private List<Entitet> entiteti;
	private Set<String> kljucevi;
	private AbstractOperator operator;
	
	public Skladiste() {
		entiteti=new ArrayList<>();
		kljucevi=new HashSet<>();
	}
	
	/**
	 * Ova metoda je za prikaz svih entiteta iz baze,
	 * menjanje entiteta liste nikad ne treba da se radi preko ove metode
	 * @return Lista entiteta u bazi za prikaz
	 */
	public List<Entitet> getEntiteti(){
		return entiteti;
	}
	
	/**
	 * Ova metoda postoji za aplikacije koje koriste efikasnije liste,
	 * Na primer ObserveableList koja odmah obavestava komponente o promeni
	 * @param entiteti Lista koja se koristi za skladistenje entiteta
	 */
	public void setEntiteti(List<Entitet> entiteti) {
		this.entiteti = entiteti;
	}
	
	/**
	 * @return Imena svih kljuceva koji se pojavljuju u trenutnog bazi
	 */
	public Set<String> getKljucevi(){
		return kljucevi;
	}
	
	/**
	 * @param entitet Entitet koji se dodaje u bazu
	 * @return -1 ako je nadjen entitet sa istim id-om, 0 ako je uspesno dodat entitet
	 */
	public int dodajEntitet(Entitet entitet) {
		for (Entitet e : entiteti) {
			if(e.getId().equals(entitet.getId())){
				return -1;
			}
			if(e.nadjiUgnjezdene()!=null) {
				if(e.nadjiUgnjezdene().contains(entitet)) {
					return -1;
				}
			}
		}
		entiteti.add(entitet);
		kljucevi.addAll(entitet.getAtributi().keySet());
		if(operator!=null) {
			operator.dodajEntitet(entitet);
		}
		return 0;
	}
	
	/**
	 * @param idSpoljasnjeg ID od entiteta u koji zelimo da smestimo ugnjezdeni entitet
	 * @param kljucSpoljasnjeg Kljuc spoljasnjeg entiteta cija ce vrednost biti ugnjezden entitet
	 * @param ugnjezden Entitet koji hocemo da ugnezdimo u spoljasni entitet
	 * @return -1 ako nije nadjen entitet na koji treba da se nakaci ugnjezden, 0 ako je uspesno dodat
	 */
	public int naknadnoDodaj(String idSpoljasnjeg, String kljucSpoljasnjeg, Entitet ugnjezden) {
		for (Entitet entitet : entiteti) {
			if(entitet.getId().equals(idSpoljasnjeg)){
				entitet.dodajUgnjezdeni(kljucSpoljasnjeg, ugnjezden);
				if(operator!=null) {
					operator.izmeniEntitet(entitet);
				}
				return 0;
			}
		}
		return -1;
	}
	
	/**
	 * Generalno koristi Uslov klasu iz specifikacije, ali podrzava i normalne Java predikate
	 * Za kompleksnije/kompozitne uslove, koristiti Predicate.AND/OR/NOT metode
	 * @param uslovi Lista uslova koji ce se proveriti za entitet
	 * @return Vraca filtriranu listu entiteta iz baze
	 */
	public List<Entitet> pretrazi(Predicate<Entitet> uslovi) {
		List<Entitet> rezultat=new ArrayList<>();
		for (Entitet entitet : entiteti) {
			if(uslovi.test(entitet)) {
				rezultat.add(entitet);
			}
		}
		return rezultat;
	}
	
	/**
	 * @param kriterijumi Lista kljuceva po cijim vrednostima se sortira, gde je pocetni kljuc prvi prioritet u sortiranju
	 * @param entiteti Lista entiteta koja se sortira po kriterijumima
	 * @return Sortirana lista entiteta
	 */
	public List<Entitet> sortiraj(List<String> kriterijumi, List<Entitet> entiteti) {
		Collections.sort(entiteti, new EntitetKomparator(kriterijumi));
		return entiteti;
	}
	
	/**
	 * @param entitet Entitet koji se brise iz baze
	 */
	public void brisi(Entitet entitet) {
		entiteti.remove(entitet);
		if(operator!=null) {
			operator.brisi(entitet);
		}
	}

	/**
	 * @return Operator koji osigurava trajno snimanje podataka, moze da bude fajl, SQL baza, etc
	 */
	public AbstractOperator getOperator() {
		return operator;
	}

	/**
	 * Ova metoda ucita entitete u bazu iz trajnih podataka zabelezenih pomocu Operatora
	 * i prosledjuje entitete iz baze da se zabeleze preko Operatora\n
	 * Ako se desi konflikt tako da ima entitet u Operatoru i entitet u bazi koji imaju isti id, entitet iz Operatora uzima prioritet
	 * @param Novi operator koji ce da rukuje trajnim snimanjem podataka baze
	 */
	public void setOperator(AbstractOperator operator) {
		this.operator = operator;
		List<Entitet> ucitaniEntiteti = operator.ucitajUSkladiste();
		List<Entitet> entitetiZaBrisanje = new ArrayList<Entitet>();
		for(Entitet entitet : entiteti) {
			if(ucitaniEntiteti.contains(entitet)) {
				entitetiZaBrisanje.add(entitet);
			} else {
				operator.dodajEntitet(entitet);
			}
		}
		entiteti.removeAll(entitetiZaBrisanje);
		entiteti.addAll(ucitaniEntiteti);
		for (Entitet e : ucitaniEntiteti) {
			kljucevi.addAll(e.getAtributi().keySet());
		}
	}

	/**
	 * @param entitet Entitet ciji atribut menjamo/dodajemo/brisemo
	 * @param kljuc Kljuc entiteta ciju vrednost menjamo
	 * @param vrednost Vrednost koja se prosledjuje u string formatu, entitet.setAtribut ce da parsira ako je u formatu koji je podrzan
	 */
	public void izmeniEntitet(Entitet entitet, String kljuc, String vrednost) {
		entitet.setAtribut(kljuc, vrednost);
		if(operator!=null) {
			operator.izmeniEntitet(entitet);
		}
	}
}