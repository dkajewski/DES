import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws IOException{
		int a = menu();
		while(a!=0){
			switch(a){
			case 1:
				new Szyfrowanie();
				break;
			case 2:
				new Deszyfrowanie();
				break;
				default: System.out.println("Wybrano niepoprawn¹ opcjê"); break;
			}
			
			a = menu();
		}
		
		
	}
	
	public static int menu(){
		
		System.out.println("1. Szyfrowanie");
		System.out.println("2. Deszyfrowanie");
		System.out.print("Wybierz tryb: ");
		@SuppressWarnings("resource")
		Scanner sca = new Scanner(System.in);
		int a = sca.nextInt();
		return a;
	}

}
