package BlackjackPackage;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Spieler {
	String nummer;
	boolean gewonnen;
	boolean verloren;
	boolean mitspielen;
	ArrayList<Karten> hilfsKarte = new ArrayList<Karten>();
	Chips c;
	int einsatz = 0;

	public Spieler(String n){
		nummer = n;
		gewonnen = false;
		//if(getSumme() == 21) gewonnen = true;
		verloren = false;
		mitspielen = true;
		zweiKartenZiehen();
		c = new Chips();

	}
	public String getNummer(){
		return nummer;
	}

	private void zweiKartenZiehen(){
		for(int i=0; i<2; i++){
			Random r = new Random();
			int rand = r.nextInt(Blackjack.stapel.size()-1);
			hilfsKarte.add(Blackjack.stapel.get(rand));
			Blackjack.stapel.remove(rand);
		}
	}

	public void karteZiehen() {
		
		if(mitspielen){	
			Random r = new Random();
			int rand = r.nextInt(Blackjack.stapel.size()-1);
			hilfsKarte.add(Blackjack.stapel.get(rand));
			Blackjack.stapel.remove(rand);
			//System.out.println(nummer + " hat eine karte gezogen: "+hilfsKarte.get(hilfsKarte.size()-1).getKarte());
			//System.out.println("Summe: "+getSumme());
			checkUeberkauft();
			//if (getSumme() < 21) System.out.println(getSumme());
			if(verloren) System.out.println(nummer+" hat sich mit einer Summe von "+getSumme()+" überkauft");

		}

	}

	private void checkUeberkauft() {
		if(getSumme() > 21){
			mitspielen = false;
			verloren = true;
		}
	}

	public int getSumme() {
		int summe = 0;
		for(int i = 0; i<hilfsKarte.size(); i++){
			summe = summe + hilfsKarte.get(i).getKarte();
		}
		return summe;
	}

	public void setEinsatz(int e) {
		
		if(c.getCredit()-e >= 0){
			einsatz = e;
			c.setCredit(einsatz);
		}
		else{
			System.out.println("Sie können maximal "+ c.getCredit() +" setzen!");
			System.out.println("Wie viel möchten Sie setzen?");
			@SuppressWarnings("resource")
			Scanner scaneinsatz = new Scanner(System.in);
			setEinsatz(scaneinsatz.nextInt());
		}
	}

	public void patt(){
		c.setCreditWin(einsatz);
	}

	public void gewinn(){
		c.setCreditWin(2*einsatz);
	}

	public int getKontostand(){
		return c.getCredit();
	}

	public void doublen(){
		karteZiehen();
		setEinsatz(einsatz);
		System.out.println("Sie haben nach dem doublen eine Summe von "+ getSumme());
		System.out.println();
	}
}
