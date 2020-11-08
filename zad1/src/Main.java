import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        int ilosc = 0;
        while(ilosc < 2 || ilosc > 100){
            System.out.print("Podaj ilosc filozofów(od 2 do 100): ");
            ilosc = scan.nextInt();
        }
        int opcja = 0;
        while(opcja < 1 || opcja > 3){
            System.out.print("Podaj wariant symulacji(od 1 do 3): ");
            opcja = scan.nextInt();
        }
        int jedzenie = 0;
        while(jedzenie != -1 && (jedzenie < 1 || jedzenie > 50)){
            System.out.print("Podaj ile razy filozof ma jesc(od 1 do 50 lub -1 jesli bez konca): ");
            jedzenie = scan.nextInt();
        }

        switch (opcja){
            case 1:
                Filozof_1.widelec = new Semaphore[ilosc];
                Filozof_1.ilosc = ilosc;
                Filozof_1.jedzenie = jedzenie;
                for (int i = 0; i< ilosc; i++) {
                    Filozof_1.widelec[i] = new Semaphore ( 1 ) ;
                }
                for (int i = 0; i< ilosc; i++) {
                    new Filozof_1(i).start();
                }
                break;
            case 2:
                Filozof_2.widelec = new Semaphore[ilosc];
                Filozof_2.ilosc = ilosc;
                Filozof_2.jedzenie = jedzenie;
                for (int i = 0; i< ilosc; i++) {
                    Filozof_2.widelec[i] = new Semaphore ( 1 ) ;
                }
                for (int i = 0; i< ilosc; i++) {
                    new Filozof_2(i).start();
                }
                break;
            case 3:
                Filozof_3.widelec = new Semaphore[ilosc];
                Filozof_3.ilosc = ilosc;
                Filozof_3.jedzenie = jedzenie;
                for (int i = 0; i< ilosc; i++) {
                    Filozof_3.widelec[i] = new Semaphore ( 1 ) ;
                }
                for (int i = 0; i< ilosc; i++) {
                    new Filozof_3(i).start();
                }
                break;

        }

    }
}
