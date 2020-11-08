import java.util.Random;

public class Port {
    static int PORT = 1;
    static int PORT_ROZLADOWANIE = 2;
    static int PORT_LADOWANIE = 3;
    static int START = 4;
    static int REJS = 5;
    static int KONIEC = 6;
    static int KATASTROFA = 7;
    static int GOTOWY = 8;

    int numerPortu;
    int iloscDokow;
    int iloscZajetych;
    int iloscStatkow;
    Random random = new Random();

    Port(int iloscDokow, int iloscStatkow, int numerPortu){
        this.iloscDokow=iloscDokow;
        this.iloscStatkow=iloscStatkow;
        this.iloscZajetych=0;
        this.numerPortu=numerPortu;
    }

    synchronized int start(int numer){
        iloscZajetych--;
        System.out.println("Port numer " + numerPortu + " pozwolenie na start statkowi " + numer);
        return START;
    }

    synchronized int dokuj(){
        try{
            Thread.currentThread().sleep(5000);
        }
        catch(Exception ie){
        }
        if(iloscZajetych<iloscDokow){
            iloscZajetych++;
            System.out.println("Pozwolenie na dokowanie w doku numer: "+iloscZajetych);
            return PORT;
        }
        else {
            System.out.println("Wszystkie doki w porcie numer " + numerPortu + " sa zajete");
            return KONIEC;
        }
    }
    synchronized void zmniejsz(){
        iloscStatkow--;
    }
    synchronized void zwieksz(){
        iloscStatkow++;
    }
    synchronized void dodajStatek(){
        Main.kolejny++;
        switch (random.nextInt(2)) {
            case 0:
                new Rejsowy(Main.kolejny, 2000, this).start();
                System.out.println("W porcie numer " + numerPortu + " zwodowanao nowy statek Rejsowy numer " + Main.kolejny);
                break;
            case 1:
                System.out.println("W porcie numer " + numerPortu + " zwodowanao nowy statek Towarowy numer " + Main.kolejny);
                new Towarowy(Main.kolejny, 10000, this).start();
                break;
        }
    }

}
