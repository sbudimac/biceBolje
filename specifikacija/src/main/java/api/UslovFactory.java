package api;

import java.time.LocalDateTime;

public class UslovFactory {
	
	public static Uslov KreirajUslov(String kljuc, Operacija operacija, String vrednost) {
		Uslov uslov = null;
		if(kljuc.equals("id") || kljuc.equals("naziv")) {
			uslov = new TekstualniUslov(kljuc, operacija, vrednost);
		} else {
			Object preradjenaVrednost = ParserPodataka.parse(vrednost);
			if(preradjenaVrednost instanceof LocalDateTime) {
				uslov = new VremenskiUslov(kljuc, operacija, (LocalDateTime)preradjenaVrednost);
			} else if(preradjenaVrednost instanceof Number) {
				uslov = new BrojevniUslov(kljuc, operacija, (Number)preradjenaVrednost);
			} else if(preradjenaVrednost instanceof String) {
				uslov = new TekstualniUslov(kljuc, operacija, (String)preradjenaVrednost);
			}
		}
		return uslov;
	}
}
