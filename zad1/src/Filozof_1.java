import java.util.concurrent.Semaphore;

public class Filozof_1 extends Thread{
    static int ilosc;
    static int jedzenie;
    int ile_razy;
    static Semaphore [] widelec;
    int mojNum;
    public Filozof_1 (int nr) {
        mojNum=nr ;
        ile_razy = 0;
    }
    public void run ( ) {
        while (ile_razy!=jedzenie) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (7000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            widelec [mojNum].acquireUninterruptibly ( ) ; //przechwycenie L widelca
            widelec [ (mojNum+1)% ilosc].acquireUninterruptibly ( ) ; //przechwycenie P widelca
// jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            ile_razy++;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec [mojNum].release ( ) ; //zwolnienie L widelca
            widelec [ (mojNum+1)% ilosc].release ( ) ; //zwolnienie P widelca
        }
        System.out.println("Filozof " + mojNum + " juz najedzony");
    }
}

