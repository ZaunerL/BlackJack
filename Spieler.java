package BlackjackPackage;

import java.util.ArrayList;
import java.util.Random;

public class Spieler {
	String nummer;
	static boolean gewonnen;
	static boolean verloren;
	static boolean mitspielen;
	ArrayList<Karten> hilfsKarte = new ArrayList<Karten>();
	
	public Spieler(String n){
		n = nummer;
		gewonnen = false;
		if(getSumme() == 21) gewonnen = true;
		verloren = false;
		mitspielen = true;
		karteZiehen();
		
	}
	public String getNummer(){
		return nummer;
	}

	public void karteZiehen() {
		if(mitspielen){
			
		Random r = new Random();
		int rand = r.nextInt(Blackjack.stapel.size()-1);
		hilfsKarte.add(Blackjack.stapel.get(rand));
		Blackjack.stapel.remove(rand);
		System.out.println(nummer + "hat eine karte gezogen: "+hilfsKarte.get(hilfsKarte.size()-1).getKarte());
		System.out.println("Summe: "+getSumme());
		gewonnenJaNein();
		if(gewonnen) System.out.println(nummer + " hat das Spiel gewonnen");
		else if(verloren) System.out.println(nummer+" hat verloren, die Summe ist höher als 21!");
		
		}
		
	}

	private void gewonnenJaNein() {
		if(getSumme() > 21){
			mitspielen = false;
			verloren = true;
		}
		if(getSumme() == 21){
			mitspielen = false;
			gewonnen = true;
		}
		
	}

	private int getSumme() {
		int summe = 0;
		for(int i = 0; i<hilfsKarte.size(); i++){
			summe = summe + hilfsKarte.get(i).getKarte();
		}
		return summe;
	}

}
