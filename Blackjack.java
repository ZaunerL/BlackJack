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
	
	public static void main(String[] args) {
		
		stapelErzeugen();
		spielerErzeugen();
		
		while(!Spieler.gewonnen){
			int x = 0;
			for(Spieler hilfsspieler : spiel){
				String hilfszeichen;
				System.out.println(hilfsspieler.nummer+ " ist an der Reihe!");
				System.out.println("Wollen Sie eine Karte ziehen?");
				hilfszeichen = scan.next();
				if(hilfszeichen.equals("y")){
					hilfsspieler.karteZiehen();
				}
				x++;
			}
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
		System.out.println("Geben Sie bitte die Spieleranzahl ein!");
		int anzSp = scan.nextInt();
		for(int i = 0; i < anzSp; i++){
			spiel.add(new Spieler("Spieler Nummer " +(i + 1)));
		}
		spiel.add(new Croupier("Croupier"));
	}

}
