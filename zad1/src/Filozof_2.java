import java.util.concurrent.Semaphore;

public class Filozof_2 extends Thread{
    static int ilosc;
    static int jedzenie;
    int ile_razy;
    static Semaphore [] widelec;
    int mojNum;
    public Filozof_2 ( int nr ) {
        mojNum=nr ;
        ile_razy = 0;
    }
    public void run ( ) {
        while (ile_razy!=jedzenie) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            if (mojNum == 0) {
                widelec [ (mojNum+1)%ilosc].acquireUninterruptibly ( ) ;
                widelec [mojNum].acquireUninterruptibly ( ) ;
            } else {
                widelec [mojNum].acquireUninterruptibly ( ) ;
                widelec [ (mojNum+1)%ilosc].acquireUninterruptibly ( ) ;
            }
// jedzenie
            System.out.println ( "Zaczyna jesc "+mojNum) ;
            ile_razy++;
            try {
                Thread.sleep ( ( long ) (3000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            System.out.println ( "Konczy jesc "+mojNum) ;
            widelec [mojNum].release ( ) ;
            widelec [ (mojNum+1)%ilosc].release ( ) ;
        }
        System.out.println("Filozof " + mojNum + " juz najedzony");
    }
}
