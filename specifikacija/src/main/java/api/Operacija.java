package api;

public enum Operacija {
	//JEDNAKO, NIJE_JEDNAKO, MANJE, VECE, VECE_JEDNAKO, MANJE_JEDNAKO, SADRZI, POCINJE_SA, ZAVRSAVA_SA
	
	JEDNAKO("JE JEDNAKO", Tip.SVI),
	NIJE_JEDNAKO("NIJE JEDNAKO", Tip.SVI),
	MANJE("MANJE OD", Tip.BROJ),
	VECE("VECE OD", Tip.BROJ),
	VECE_JEDNAKO("VECE ILI JEDNAKO OD", Tip.BROJ),
	MANJE_JEDNAKO("MANJE ILI JEDNAKO OD", Tip.BROJ),
	SADRZI("SADRZI", Tip.TEKST),
	POCINJE_SA("POCINJE SA", Tip.TEKST),
	ZAVRSAVA_SA("ZAVRSAVA SE SA", Tip.TEKST);
	
	private final String tekstualniOblik;
	private final Tip tip;
	
	Operacija(String tekstualniOblik, Tip tip){
		this.tekstualniOblik=tekstualniOblik;
		this.tip=tip;
	}
	
	@Override
	public String toString() {
		return tekstualniOblik;
	}
	
	public Tip getTip() {
		return tip;
	}
}
