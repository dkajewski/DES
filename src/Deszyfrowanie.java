import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Deszyfrowanie {

	byte[] pPocz¹tkowa = { 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5,
			63, 55, 47, 39, 31, 23, 15, 7, 56, 48, 40, 32, 24, 16, 8, 0, 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36,
			28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6 };

	byte[] Pkoñcowa = { 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36,
			4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49,
			17, 57, 25, 32, 0, 40, 8, 48, 16, 56, 24 };

	byte[] PC1 = { 56, 48, 40, 32, 24, 16, 8, 0, 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51,
			43, 35,

			62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 60, 52, 44, 36, 28, 20, 12, 4, 27, 19, 11,
			3 };

	byte[] PC2 = { 13, 16, 10, 23, 0, 4, 2, 27, 14, 5, 20, 9, 22, 18, 11, 3, 25, 7, 15, 6, 26, 19, 12, 1, 40, 51, 30,
			36, 46, 54, 29, 39, 50, 44, 32, 47, 43, 48, 38, 55, 33, 52, 45, 41, 49, 35, 28, 31 };

	byte[] pR = { 31, 0, 1, 2, 3, 4, 3, 4, 5, 6, 7, 8, 7, 8, 9, 10, 11, 12, 11, 12, 13, 14, 15, 16, 15, 16, 17, 18, 19,
			20, 19, 20, 21, 22, 23, 24, 23, 24, 25, 26, 27, 28, 27, 28, 29, 30, 31, 0 };

	byte[][] S1 = { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
			{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
			{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
			{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };
	byte[][] S2 = { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
			{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
			{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
			{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };
	byte[][] S3 = { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
			{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
			{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
			{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
	byte[][] S4 = { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
			{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
			{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
			{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };
	byte[][] S5 = { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
			{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
			{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
			{ 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };
	byte[][] S6 = { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
			{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
			{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
			{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };
	byte[][] S7 = { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
			{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
			{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
			{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };
	byte[][] S8 = { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
			{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
			{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
			{ 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

	byte[] Pblok = { 15, 6, 19, 20, 28, 11, 27, 16, 0, 14, 22, 25, 4, 17, 30, 9, 1, 7, 23, 13, 31, 26, 2, 8, 18, 12, 29,
			5, 21, 10, 3, 24 };

	String[][] lewa = new String[4][8];
	String[][] prawa = new String[4][8];
	String bit56 = "";
	String lewyKlucz = "";
	String prawyKlucz = "";

	String szyfr;
	String szyfrBinarnie;
	String klucz;
	String kluczBinarnie;

	List<String> L = new ArrayList<String>();
	List<String> R = new ArrayList<String>();
	List<String> CD = new ArrayList<String>();
	List<String> KS = new ArrayList<String>();
	List<String> E = new ArrayList<String>();
	List<String> EKSxor = new ArrayList<String>();
	List<String> Sboxy = new ArrayList<String>();
	List<String> P = new ArrayList<String>();

	List<byte[][]> Sbox = new ArrayList<byte[][]>();

	public Deszyfrowanie() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Podaj szyfr: ");
		szyfr = sc.nextLine();
		System.out.print("Podaj klucz: ");
		klucz = sc.nextLine();

		if (szyfr.length() == 8) {
			szyfr = asciiToHex(szyfr);
		}

		szyfrBinarnie = hexToBinary(szyfr);
		podzia³();

		String lewo = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				lewo += lewa[i][j];
			}
		}

		String prawo = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				prawo += prawa[i][j];
			}
		}

		R.add(prawo);
		L.add(lewo);
		Sbox.add(S1);
		Sbox.add(S2);
		Sbox.add(S3);
		Sbox.add(S4);
		Sbox.add(S5);
		Sbox.add(S6);
		Sbox.add(S7);
		Sbox.add(S8);

		kluczBinarnie = hexToBinary(klucz);
		pPC1();

		utwórzKlucze();
		
		for (int i = 16; i >= 1; i--) {
			// rozszerzenie lewej strony kodu
			String rozszerzonaPrawa = pRozszerzaj¹ca(prawo);
			E.add(rozszerzonaPrawa);
			// E xor KS
			String ExorKS = XOR(rozszerzonaPrawa, KS.get(i));
			EKSxor.add(ExorKS);
			// E xor KS do sBoxów
			String nowaPrawa = sBoxy(ExorKS);
			// permutacja P-bloku
			nowaPrawa = pP(nowaPrawa);
			P.add(nowaPrawa);
			// XOR na wyniku permutacji P-bloku i lewej
			nowaPrawa = XOR(nowaPrawa, lewo);
			lewo = prawo;
			prawo = nowaPrawa;

			L.add(lewo);
			R.add(prawo);

			if (i <= 1) {
				prawyDoLewegoWypijKolego(lewo, prawo);
			}

			
		}
		
		generujRaport();

		// permutacja koñcowa, czyli pocz¹tkowa w sumie
		String szyfr = pK(lewo, prawo);
		System.out.println("Output: " + odstêpy8bit(szyfr));
		szyfr = BinToHex(szyfr);
		System.out.println("Wiadomoœæ (HEX): " + szyfr);
		System.out.println("Wiadomoœæ (ASCII): " + hexToASCII(szyfr));
	}

	// ASCII do HEX
	public String asciiToHex(String asciiValue) {
		char[] chars = asciiValue.toCharArray();
		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}
		return hex.toString();
	}

	// szesnastkowy kod do binarnego na warzywo
	String hexToBinary(String hex) {
		String wynik = "";
		int d³ugoœæ = hex.length();
		while (d³ugoœæ > 0) {
			switch (hex.charAt(d³ugoœæ - 1) + "") {
			case "0":
				wynik = "0000" + wynik;
				break;
			case "1":
				wynik = "0001" + wynik;
				break;
			case "2":
				wynik = "0010" + wynik;
				break;
			case "3":
				wynik = "0011" + wynik;
				break;
			case "4":
				wynik = "0100" + wynik;
				break;
			case "5":
				wynik = "0101" + wynik;
				break;
			case "6":
				wynik = "0110" + wynik;
				break;
			case "7":
				wynik = "0111" + wynik;
				break;
			case "8":
				wynik = "1000" + wynik;
				break;
			case "9":
				wynik = "1001" + wynik;
				break;
			case "a":
			case "A":
				wynik = "1010" + wynik;
				break;
			case "b":
			case "B":
				wynik = "1011" + wynik;
				break;
			case "c":
			case "C":
				wynik = "1100" + wynik;
				break;
			case "d":
			case "D":
				wynik = "1101" + wynik;
				break;
			case "e":
			case "E":
				wynik = "1110" + wynik;
				break;
			case "f":
			case "F":
				wynik = "1111" + wynik;
				break;
			}
			d³ugoœæ--;
		}
		return wynik;
	}

	// podzia³ na lew¹ i praw¹ stronê
	public void podzia³() {
		String pom = "";
		for (int i = 0; i < 64; i++) {
			pom += szyfrBinarnie.charAt(pPocz¹tkowa[i]) + "";
		}

		int a = 0;
		while (a < 32) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					lewa[i][j] = pom.charAt(a) + "";
					a++;
				}
			}
		}

		a = 32;
		while (a < 64) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++) {
					prawa[i][j] = pom.charAt(a) + "";
					a++;
				}
			}
		}

	}

	// permutacja PC-1
	public void pPC1() {
		for (int i = 0; i < PC1.length; i++) {
			bit56 += kluczBinarnie.charAt(PC1[i]);
		}
		lewyKlucz = bit56.substring(0, 28);
		prawyKlucz = bit56.substring(28, 56);
		CD.add(bit56);
		// System.out.println("0: Lewy: "+lewyKlucz+ " Prawy: "+prawyKlucz);

	}

	// stworzenie kluczy
	public void utwórzKlucze() {
		KS.add("0");
		for (int i = 1; i < 17; i++) {
			przesuñBity(i);
			KS.add(pPC2(i));
		}
	}

	public void przesuñBity(int i) {
		switch (i) {
		case 1:
		case 2:
		case 9:
		case 16:
			// przesuwamy bity o 1 w lewo
			lewyKlucz = lewyKlucz.substring(1, 28) + lewyKlucz.charAt(0);
			prawyKlucz = prawyKlucz.substring(1, 28) + prawyKlucz.charAt(0);
			// System.out.println(i +": Lewy: "+lewyKlucz+ " Prawy:
			// "+prawyKlucz);
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			// przesuwamy bity o 2 w lewo
			lewyKlucz = lewyKlucz.substring(2, 28) + lewyKlucz.substring(0, 2);
			prawyKlucz = prawyKlucz.substring(2, 28) + prawyKlucz.substring(0, 2);
			// System.out.println(i +": Lewy: "+lewyKlucz+ " Prawy:
			// "+prawyKlucz);
			break;
		}
		CD.add(lewyKlucz + prawyKlucz);
	}

	// permutacja PC-2
	public String pPC2(int index) {
		String niewiarygodneZjednoczenie = CD.get(index);
		// CD.add(niewiarygodneZjednoczenie);
		String wynik = "";
		for (int i = 0; i < 48; i++) {
			wynik += niewiarygodneZjednoczenie.charAt(PC2[i]);
		}
		return wynik;

	}

	// permutacja rozszerzaj¹ca
	public String pRozszerzaj¹ca(String prawa) {
		String wynik = "";
		String pom = prawa;
		for (int i = 0; i < 48; i++) {
			wynik += pom.charAt(pR[i]);
		}

		return wynik;
	}

	// xorowanie
	public String XOR(String rPrawa, String klucz48) {
		String wynik = "";
		for (int i = 0; i < klucz48.length(); i++) {
			if (((rPrawa.charAt(i) + "").equals("1") && (klucz48.charAt(i) + "").equals("1"))
					|| ((rPrawa.charAt(i) + "").equals("0") && (klucz48.charAt(i) + "").equals("0"))) {
				wynik += "0";
			} else {
				wynik += "1";
			}
		}

		return wynik;
	}

	// SBOXy
	public String sBoxy(String dane) {
		String wynik = "";
		String[] tab6bit = new String[8];
		// dzielenie na 6-bitowe ci¹gi
		int x = 0;
		int y = 6;
		int licznik = 0;
		while (licznik <= 7) {
			tab6bit[licznik] = dane.substring(x, y);
			x = y;
			y += 6;
			licznik++;
		}

		// lecimy do boxów
		for (int i = 0; i < 8; i++) {
			String wiersz = tab6bit[i].charAt(0) + "" + tab6bit[i].charAt(5);
			String kolumna = tab6bit[i].substring(1, 5);
			wynik += Bin(Sbox.get(i)[Integer.valueOf(wiersz, 2)][Integer.valueOf(kolumna, 2)]);
		}
		// System.out.println("sBOX: "+wynik);
		Sboxy.add(wynik);
		return wynik;
	}

	// int do binarnego na warzywo
	public String Bin(byte a) {
		switch (a) {
		case 10:
			return "1010";
		case 11:
			return "1011";
		case 12:
			return "1100";
		case 13:
			return "1101";
		case 14:
			return "1110";
		case 15:
			return "1111";
		case 0:
			return "0000";
		case 1:
			return "0001";
		case 2:
			return "0010";
		case 3:
			return "0011";
		case 4:
			return "0100";
		case 5:
			return "0101";
		case 6:
			return "0110";
		case 7:
			return "0111";
		case 8:
			return "1000";
		case 9:
			return "1001";
		default:
			return null;
		}
	}

	// permutacja P-bloku
	public String pP(String dane) {
		String wynik = "";
		for (int i = 0; i < 32; i++) {
			wynik += dane.charAt(Pblok[i]);
		}
		return wynik;
	}

	public void prawyDoLewegoWypijKolego(String lewo, String prawo) {
		int licznik = 0;
		lewa = new String[4][8];
		prawa = new String[4][8];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 8; j++) {
				lewa[i][j] = lewo.charAt(licznik) + "";
				prawa[i][j] = prawo.charAt(licznik) + "";
				licznik++;
			}
		}
	}

	// permutacja koñcowa
	public String pK(String lewo, String prawo) {
		String wynik = "";
		String pom = prawo + lewo;

		for (int i = 0; i < pom.length(); i++) {
			wynik += pom.charAt(Pkoñcowa[i]);
		}
		return wynik;
	}

	public String odstêpy8bit(String dane) {
		String wynik = "";
		int a = dane.length();
		while (a > 0) {
			wynik = dane.substring(a - 8, a) + " " + wynik;
			a -= 8;
		}
		return wynik;
	}

	public String BinToHex(String bin) {
		String wynik = "";
		int licznik = bin.length();
		while (licznik > 0) {
			switch (bin.substring(licznik - 4, licznik)) {
			case "0000":
				wynik = "0" + wynik;
				break;
			case "0001":
				wynik = "1" + wynik;
				break;
			case "0010":
				wynik = "2" + wynik;
				break;
			case "0011":
				wynik = "3" + wynik;
				break;
			case "0100":
				wynik = "4" + wynik;
				break;
			case "0101":
				wynik = "5" + wynik;
				break;
			case "0110":
				wynik = "6" + wynik;
				break;
			case "0111":
				wynik = "7" + wynik;
				break;
			case "1000":
				wynik = "8" + wynik;
				break;
			case "1001":
				wynik = "9" + wynik;
				break;
			case "1010":
				wynik = "a" + wynik;
				break;
			case "1011":
				wynik = "b" + wynik;
				break;
			case "1100":
				wynik = "c" + wynik;
				break;
			case "1101":
				wynik = "d" + wynik;
				break;
			case "1110":
				wynik = "e" + wynik;
				break;
			case "1111":
				wynik = "f" + wynik;
				break;
			}

			licznik -= 4;
		}

		return wynik;
	}

	// HEX do ASCII
	public String hexToASCII(String hexValue) {
		StringBuilder output = new StringBuilder("");
		for (int i = 0; i < hexValue.length(); i += 2) {
			String str = hexValue.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}
		return output.toString();
	}
	
	public void generujRaport(){
		int licznik=16;
		System.out.println("Input bits: "+odstêpy8bit(szyfrBinarnie));
		System.out.println("Key bits:   "+odstêpy8bit(kluczBinarnie));
		for(int i=0; i<CD.size(); i++){
			System.out.println("CD["+i+"]: "+odstêpy7bit(CD.get(i)));
			if(i!=0){
				System.out.println("KS["+i+"]: "+odstêpy6bit(KS.get(i)));
			}
		}
		System.out.println("L[0]: "+odstêpy8bit(L.get(0)));
		System.out.println("R[0]: "+odstêpy8bit(R.get(0)));
		
		for(int i=0; i<16; i++){
			System.out.println("Round "+(i+1));
			System.out.println("E:        "+odstêpy6bit(E.get(i)));
			System.out.println("KS:       "+odstêpy6bit(KS.get(licznik)));
			System.out.println("E xor KS: "+odstêpy6bit(EKSxor.get(i)));
			System.out.println("Sbox:     "+odstêpy4bit(Sboxy.get(i)));
			System.out.println("P:        "+odstêpy8bit(P.get(i)));
			System.out.println("L["+(i+1)+"]: "+odstêpy8bit(L.get(i+1)));
			System.out.println("R["+(i+1)+"]: "+odstêpy8bit(R.get(i+1)));
			if(i==15){
				System.out.println("LR[16]: "+odstêpy8bit(R.get(16)+L.get(16)));
			}
			licznik--;
		}
	}
	
	public String odstêpy7bit(String dane){
		String wynik="";
		int a = dane.length();
		while(a>0){
			wynik=dane.substring(a-7, a)+" "+wynik;
			a-=7;
			
		}
		return wynik;
	}
	
	public String odstêpy6bit(String dane){
		String wynik="";
		int a = dane.length();
		while(a>0){
			wynik=dane.substring(a-6, a)+" "+wynik;
			a-=6;
			
		}
		return wynik;
	}
	
	public String odstêpy4bit(String dane){
		String wynik="";
		int a = dane.length();
		while(a>0){
			wynik=dane.substring(a-4, a)+" "+wynik;
			a-=4;
			
		}
		return wynik;
	}
}
