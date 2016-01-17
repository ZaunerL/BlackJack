package BlackjackPackage;
/**
 * 0 = Ass (entspricht 1 oder 11)
 * 1 = 2
 * 2 = 3
 * 3 = 4
 * 4 = 5
 * 5 = 6
 * 6 = 7
 * 7 = 8
 * 8 = 9
 * 9 = 10
 * 10 = Bube, Dame, König (entspricht 10)
 * @author LUKAS
 *
 */
public class Karten {
	int zahl;

	public Karten(int z){
		z++;
		if((int)z%13 > 10) zahl = 10;
		else zahl = (int)z%13;
		if(zahl == 0){
			zahl = 1;
		}
	}
	public int getKarte(){
		return zahl;
	}

}
