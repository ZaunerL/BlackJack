package BlackjackPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

	static Scanner scan = new Scanner(System.in);
	static int anzStapel = 6;
	static int anzKarten = 52;
	int anzGes = anzKarten*anzStapel;
	static ArrayList<Spieler> spiel = new ArrayList<Spieler>();
	static ArrayList<Karten> stapel = new ArrayList<Karten>();
	static Croupier c;
	static boolean nextround = true;

	public static void main(String[] args) {

		stapelErzeugen();
		
		while(spiel.size() == 0){
			spielerErzeugen();			
		}


		while(nextround && spiel.size() > 1){
			einsatzFordern();
			play();
			sucheGewinn();
			askNextRound();
			allPlayersStillIn();
		}
	}

	private static void stapelErzeugen(){
		for(int i = 0; i < anzStapel; i++){
			for(int j = 0; j < anzKarten; j++){
				stapel.add(new Karten(j));
			}
		}
	}

	private static void spielerErzeugen(){
		
		int anzSp;

		System.out.println("Geben Sie bitte die Spieleranzahl ein!");
		anzSp = scan.nextInt();
		if(anzSp > 7){
			System.out.println("Es dürfen maximal sieben Spieler an einem Tisch spielen!");
			anzSp = 0;
		}
		
		for(int i = 0; i < anzSp; i++){
			spiel.add(new Spieler("Spieler Nummer " +(i + 1)));
		}

		if(anzSp > 0){
			c = new Croupier("Croupier");
			spiel.add(c);
			
			for(int i=0; i< 50; i++){
				System.out.println();
			}
		}
	}

	private static void play(){

		

		for(Spieler hilfsspieler : spiel){
			String hilfszeichen = "y";
			
			int anz = 1; //zählt mit wie oft er bereits eine Karte gezogen hat

			if(hilfsspieler != c){
				while(hilfszeichen.equals("y") && hilfsspieler.getSumme() < 22){
					if(anz == 1){

						System.out.println(hilfsspieler.nummer+ " ist an der Reihe! ("+hilfsspieler.getSumme()+")");
						System.out.println("Wollen Sie eine Karte ziehen oder doublen? (y/n/d)");
						hilfszeichen = scan.next();
						if(hilfszeichen.equals("y")){
							hilfsspieler.karteZiehen();
						}
						else if(hilfszeichen.equals("d")){
							if(hilfsspieler.c.getCredit() - hilfsspieler.einsatz > 0){
								hilfsspieler.doublen();	
							}
							else System.out.println("Sie haben zu wenig Geld zum Doubeln.");
						}
					}
					else if ( hilfsspieler.getSumme() < 21){
						System.out.println(hilfsspieler.nummer+ " ist an der Reihe! ("+hilfsspieler.getSumme()+")");
						System.out.println("Wollen Sie eine Karte ziehen? (y/n)");
						hilfszeichen = scan.next();
						if(hilfszeichen.equals("y")){
							hilfsspieler.karteZiehen();
						}
					}
					else if (hilfsspieler.getSumme() == 21){
						System.out.println("Sie haben ein Summe von 21 gratuliere!");
						hilfszeichen = "n";
					}
					else if (hilfsspieler.getSumme() == 21){  //hier black jack checken!
						System.out.println("Sie haben ein Summe von 21 gratuliere!");
						hilfszeichen = "n";
					}
					anz++;
				}
			}

			else{

				while(c.getSumme() < 17){
					c.karteZiehen();
				}
				System.out.println("Croupier hat eine Summe von "+c.getSumme());
			}


		}
	}

	private static void einsatzFordern() {

		for(Spieler hilfsspieler : spiel){
			if(hilfsspieler != c){
				System.out.println(hilfsspieler.nummer + " Wie viel wollen Sie setzen?");
				int einsatz = scan.nextInt();
				hilfsspieler.setEinsatz(einsatz);
			}
		}
	}

	private static void sucheGewinn() {

		for(Spieler hilfsspieler : spiel){

			if(hilfsspieler != c){
				if(hilfsspieler.getSumme() == c.getSumme() && hilfsspieler.getSumme()<22){
					hilfsspieler.patt();
					System.out.printf("%s Ihr aktueller Kontostand nach dieser Runde beträgt: %d Euro. Sie haben weder gewonnen noch verloren.\n", hilfsspieler.nummer, hilfsspieler.getKontostand());
				}
				else if(hilfsspieler.getSumme() > c.getSumme() && hilfsspieler.getSumme()<22){
					hilfsspieler.gewinn();
					System.out.printf("%s Ihr aktueller Kontostand nach dieser Runde beträgt: %d Euro. Sie haben gewonnen!\n", hilfsspieler.nummer, hilfsspieler.getKontostand());
				}
				else if(hilfsspieler.getSumme() > 22){
					System.out.printf("%s Ihr aktueller Kontostand nach dieser Runde beträgt: %d Euro. Sie haben verloren.\n", hilfsspieler.nummer, hilfsspieler.getKontostand());
				}
				else{
					System.out.println("Kann dieser Fall eintreten??");
				}
			}
		}

		System.out.println();
	}

	private static void allPlayersStillIn(){
		for(Spieler hilfsspieler : spiel){
			if(hilfsspieler.getKontostand() <= 0){
				spiel.remove(hilfsspieler);
				System.out.println(hilfsspieler.nummer + " Hat kein Guthaben mehr. Er wurde vom Tisch verwiesen.");
			}
			System.out.println();
		}
	}

	private static void askNextRound(){

		System.out.println("Wollen Sie noch eine weitere Runde Spielen? [y/n]");
		if(scan.next().equals("y")){
			nextround = true;
		}
		else nextround = false;

		System.out.println();
	}
}
