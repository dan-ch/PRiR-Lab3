import java.util.Random;

public abstract class Statek extends Thread{
    static int PORT = 1;
    static int PORT_ROZLADOWANIE = 2;
    static int PORT_LADOWANIE = 3;
    static int START = 4;
    static int REJS = 5;
    static int KONIEC = 6;
    static int KATASTROFA = 7;
    static int GOTOWY = 8;

    int numer;
    int paliwo;
    int stan;
    Port port;
    Random rand;

    public abstract void zatankuj();
    public abstract void odbierzPaliwo();
    public abstract boolean czyRezerwa();
    public abstract String ladowanie();
    public abstract String rozladowanie();

    public void run(){
        while(stan!=KATASTROFA){
            if(stan==PORT){
                stan = PORT_ROZLADOWANIE;
                zatankuj();
                System.out.println("W porcie numer " + port.numerPortu + " statek " + getClass().getName() + " numer " + numer);
            }
            else if(stan==START){
                System.out.println("Statek " + getClass().getName() + " numer " + numer + " rusza");
                stan = REJS;
                port.zmniejsz();
                port = Main.zmienPort();
                port.zwieksz();
            }
            else if(stan==REJS){
                odbierzPaliwo();
                System.out.println("Statek " + getClass().getName() + " numer " + numer + " jest na wodzie");
                if(rand.nextInt(100)%3==0){
                    System.out.println("Statek " + getClass().getName() + " o numerze " + numer + " sie rozbil");
                    stan=KATASTROFA;
                }
                else if(czyRezerwa() || rand.nextInt(100)%5==0){
                    stan = KONIEC;
                }
                else try{
                    sleep(rand.nextInt(6000)+1000);
                } catch (InterruptedException e) {
                }
            }
            else if(stan==KONIEC){
                System.out.println("Porsze o pozwolenie na dokowanie w porcie numer " + port.numerPortu +
                                " statek " + getClass().getName() + " numer " + numer);
                stan=port.dokuj();
            }
            else if(stan==PORT_ROZLADOWANIE){
                System.out.println("Statek " + getClass().getName() + " numer " + numer + rozladowanie());
                stan=PORT_LADOWANIE;
                try {
                    sleep(rand.nextInt(8000)+1000);
                } catch (InterruptedException e) {
                }
            }
            else if(stan==PORT_LADOWANIE){
                System.out.println("Statek " + getClass().getName() + " numer " + numer + ladowanie());
                stan=GOTOWY;
                try {
                    sleep(rand.nextInt(8000)+1000);
                } catch (InterruptedException e) {
                }
            }
            else if(stan==GOTOWY){
                System.out.println("Statek " + getClass().getName() + " numer " + numer + " gotowy do rejsu");
                if(rand.nextInt(2)==1){
                    stan=port.start(numer);
                }
                else try{
                    sleep(rand.nextInt(8000)+1000);
                } catch (InterruptedException e) {
                }
            }
        }
        port.dodajStatek();
    }


}
