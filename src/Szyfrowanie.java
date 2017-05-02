import java.util.Random;
import java.util.Scanner;

public class Szyfrowanie {

	byte[] pPocz¹tkowa={
			57, 49, 41, 33, 25, 17, 9, 1,
			59, 51, 43, 35, 27, 19, 11, 3,
			61, 53, 45, 37, 29, 21, 13, 5,
			63, 55, 47, 39, 31, 23, 15, 7,
			56, 48, 40, 32, 24, 16, 8, 0,
			58, 50, 42, 34, 26, 18, 10, 2,
			60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14, 6
	};
	
	byte[] PC1={
			56, 48, 40, 32, 24, 16, 8,
			0, 57, 49, 41, 33, 25, 17,
			9, 1, 58, 50, 42, 34, 26,
			18, 10, 2, 59, 51, 43, 35,
			
			62, 54, 46, 38, 30, 22, 14,
			6, 61, 53, 45, 37, 29, 21,
			13, 5, 60, 52, 44, 36, 28,
			20, 12, 4, 27, 19, 11, 3
	};
	
	byte[] PC2={
			13, 16, 10, 23, 0, 4,
			2, 27, 14, 5, 20, 9,
			22, 18, 11, 3, 25, 7,
			15, 6, 26, 19, 12, 1,
			40, 51, 30, 36, 46, 54,
			29, 39, 50, 44, 32, 47,
			43, 48, 38, 55, 33, 52,
			45, 41, 49, 35, 28, 31
	};
	
	String[][] lewa = new String[4][8];
	String[][] prawa = new String[4][8];
	String bit56 = "";
	String lewyKlucz = "";
	String prawyKlucz = "";
	
	String kod;
	String kodBinarnie;
	String klucz;
	String kluczBinarnie;
	
	public Szyfrowanie(){
		String zgoda="y";
		System.out.print("Podaj wiadomoœæ do zaszyfrowania (HEX): ");
		Scanner sc = new Scanner(System.in);
		kod = sc.nextLine();
		while(kod.length()!=16){
			System.out.print("Podaj wiadomoœæ do zaszyfrowania (HEX): ");
			kod = sc.nextLine();
		}
		
		kodBinarnie = hexToBinary(kod);
		podzia³();
		System.out.print("Wygenerowaæ klucz? (y/n): ");
		zgoda = sc.nextLine().toLowerCase();
		if(zgoda.equals("y")){
			generujKlucz();
			System.out.println("Klucz: "+klucz);
		}else{
			klucz="";
			while(klucz.length()!=16){
				System.out.print("Podaj klucz 16 znaków (HEX): ");
				klucz=sc.nextLine();
			}
		}
		
		kluczBinarnie = hexToBinary(klucz);
		pPC1();
		//System.out.println(lewyKlucz+" "+prawyKlucz);
		for(int i=1; i<=16; i++){
			przesuñBity(i);
			//System.out.println(lewyKlucz+" "+prawyKlucz);
			String bit48 = pPC2();
			
			//teraz fejstel lol
		}
		sc.close();
		
	}
	
	//szesnastkowy kod do binarnego na warzywo
	String hexToBinary(String hex) {
		String bin1 = "";
		String bin2 = "";
		String bit0 = Integer.toBinaryString(Integer.parseInt(hex.charAt(0)+"", 16));
		switch(bit0.length()%4){
		case 1:
			bit0 = "000"+bit0;
			break;
		case 2:
			bit0 = "00"+bit0;
			break;
		case 3:
			bit0 = "0"+bit0;
			break;
		}
		String bit8 = Integer.toBinaryString(Integer.parseInt(hex.charAt(8)+"", 16));
		switch(bit8.length()%4){
		case 1:
			bit8 = "000"+bit8;
			break;
		case 2:
			bit8 = "00"+bit8;
			break;
		case 3:
			bit8 = "0"+bit8;
			break;
		}
		String hex1 = hex.substring(1, 8);
		String hex2 = hex.substring(9);
	    int i = Integer.parseInt(hex1, 16);
	    bin1 += Integer.toBinaryString(i);
	    
	    if(bin1.length()%4!=0){
	    	switch(bin1.length()%4){
	    	case 1:
	    		bin1 = "000"+bin1;
	    		break;
	    	case 2:
	    		bin1 = "00"+bin1;
	    		break;
	    	case 3:
	    		bin1 = "0"+bin1;
	    		break;
	    	}
	    }
	    i = Integer.parseInt(hex2, 16);
	    bin2 += Integer.toBinaryString(i);
	    if(bin2.length()%4!=0){
	    	switch(bin2.length()%4){
	    	case 1:
	    		bin2 = "000"+bin2;
	    		break;
	    	case 2:
	    		bin2 = "00"+bin2;
	    		break;
	    	case 3:
	    		bin2 = "0"+bin2;
	    		break;
	    	}
	    }
	    String bin3 = bit0+bin1+bit8+bin2;
	    return bin3;
	}
	
	//podzia³ na lew¹ i praw¹ stronê
	public void podzia³(){
		int k=0;
		while(k<=60){
			for(int i=0; i<4; i++){
				for(int j=0; j<8; j++){
					lewa[i][j]=kodBinarnie.charAt(pPocz¹tkowa[k])+"";
					k++;
					switch(k){
					case 4: case 12: case 20: case 28: case 36: case 44: case 52: case 60:
						k+=4;
						break;
					}
				}
			}
		}
		k=4;
		while(k<=63){
			for(int i=0; i<4; i++){
				for(int j=0; j<8; j++){
					prawa[i][j]=kodBinarnie.charAt(pPocz¹tkowa[k])+"";
					k++;
					switch(k){
					case 8: case 16: case 24: case 32: case 40: case 48: case 56:
						k+=4;
						break;
					}
				}
			}
		}
	}
	
	public void generujKlucz(){
		klucz="";
		Random r = new Random();
		for(int i=0; i<16; i++){
			int a = r.nextInt(16);
			switch(a){
			case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9:
				klucz+=a;
				break;
			case 10:
				klucz+="a";
				break;
			case 11:
				klucz+="b";
				break;
			case 12: 
				klucz+="c";
				break;
			case 13:
				klucz+="d";
				break;
			case 14:
				klucz+="e";
				break;
			case 15:
				klucz+="f";
				break;
			}
		}
	}
	
	//permutacja PC-1
	public void pPC1(){
		for(int i=0; i<PC1.length; i++){
			bit56+=kluczBinarnie.charAt(PC1[i]);
		}
		lewyKlucz=bit56.substring(0, 28);
		prawyKlucz=bit56.substring(28, 56);
	}
	
	public void przesuñBity(int i){
		switch(i){
		case 1: case 2: case 9: case 16:
			//przesuwamy bity o 1 w lewo
			lewyKlucz=lewyKlucz.substring(1, 28)+lewyKlucz.charAt(0);
			prawyKlucz=prawyKlucz.substring(1, 28)+prawyKlucz.charAt(0);
			break;
		case 3: case 4: case 5: case 6: case 7: case 8: case 10:
		case 11: case 12: case 13: case 14: case 15:
			//przesuwamy bity o 2 w lewo
			lewyKlucz=lewyKlucz.substring(2, 28)+lewyKlucz.substring(0, 2);
			prawyKlucz=prawyKlucz.substring(2, 28)+prawyKlucz.substring(0, 2);
			break;
		}
	}
	
	//permutacja PC-2
	public String pPC2(){
		String niewiarygodneZjednoczenie=lewyKlucz+prawyKlucz;
		String wynik="";
		for(int i=0; i<48; i++){
			wynik+=niewiarygodneZjednoczenie.charAt(PC2[i]);
		}
		return wynik;
		
	}
}
