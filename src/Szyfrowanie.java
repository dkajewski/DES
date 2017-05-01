import java.util.Scanner;

public class Szyfrowanie {

	int[] pPocz¹tkowa={
			58, 50, 42, 34, 26, 18, 10, 2,
			60, 52, 44, 36, 28, 20, 12, 4,
			62, 54, 46, 38, 30, 22, 14, 6,
			64, 56, 48, 40, 32, 24, 16, 8,
			57, 49, 41, 33, 25, 17, 9, 1,
			59, 51, 43, 35, 27, 19, 11, 3,
			61, 53, 45, 37, 29, 21, 13, 5,
			63, 55, 47, 39, 31, 23, 15, 7
	};
	
	int[][] lewa = new int[8][4];
	int[][] prawa = new int[8][4];
	
	String kod;
	String kodBinarnie;
	
	public Szyfrowanie(){
		System.out.print("Podaj wiadomoœæ do zaszyfrowania (HEX): ");
		Scanner sc = new Scanner(System.in);
		kod = sc.nextLine();
		while(kod.length()!=16){
			System.out.print("Podaj wiadomoœæ do zaszyfrowania (HEX): ");
			kod = sc.nextLine();
		}
		//System.out.println(hexToBinary(kod));
		sc.close();
		kodBinarnie = hexToBinary(kod);
		podzia³();
		
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
	    System.out.println(bin3.length());
	    return bin3;
	}
	
	//podzia³ na lew¹ i praw¹ stronê
	public void podzia³(){
		int k=0;
		while(k<=58){
			for(int i=0; i<4; i++){
				for(int j=0; j<8; j++){
					lewa[i][j]=kodBinarnie.charAt(pPocz¹tkowa[k]);
					k++;
					switch(k){
					case 4: case 12: case 20: case 28: case 34: case 42: case 50:
						k+=4;
						break;
					}
				}
			}
		}
		k=4;
		while(k<=64){
			for(int i=0; i<4; i++){
				for(int j=0; j<8; j++){
					prawa[i][j]=kodBinarnie.charAt(pPocz¹tkowa[k]);
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
}
