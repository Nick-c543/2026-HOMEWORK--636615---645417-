package diadia;

public class StanzaTest2 {

	public static void main(String[] args) {
		
		Stanza bar= new Stanza("bar"); 
		Stanza mensa=new Stanza("mensa");
		
		bar.impostaStanzaAdiacente("nord",mensa);
		mensa.impostaStanzaAdiacente("sud",bar);
		
		Attrezzo tazzina= new Attrezzo("tazzina",1);
		bar.addAttrezzo(tazzina);
		
		Attrezzo piatto= new Attrezzo("piatto",2);
		mensa.addAttrezzo(piatto);
		
		
		System.out.println(bar.getStanzaAdiacente("nord").getAttrezzi()[0]);
		System.out.println(mensa.getStanzaAdiacente("sud").getAttrezzi()[0]);
	}

}
