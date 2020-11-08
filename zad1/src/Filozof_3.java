import java.util.Random;
import java.util.concurrent.Semaphore;

public class Filozof_3 extends Thread{
    static int ilosc;
    static int jedzenie;
    int ile_razy;
    int proby;
    static Semaphore [] widelec;
    int mojNum;
    Random losuj ;
    public Filozof_3 ( int nr ) {
        mojNum=nr ;
        ile_razy = 0;
        proby = 0;
        losuj = new Random(mojNum) ;
    }
    public void run ( ) {
        while (ile_razy!=jedzenie) {
// myslenie
            System.out.println ( "Mysle ¦ " + mojNum) ;
            try {
                Thread.sleep ( ( long ) (5000 * Math.random( ) ) ) ;
            } catch ( InterruptedException e ) {
            }
            int strona = losuj.nextInt ( 2 ) ;
            boolean podnioslDwaWidelce = false ;
            do {
                if ( strona == 0) {
                    widelec [mojNum].acquireUninterruptibly ( ) ;
                    if( ! ( widelec [ (mojNum+1)%ilosc].tryAcquire ( ) ) ) {
                        widelec[mojNum].release ( ) ;
                        proby++;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                } else {
                    widelec[(mojNum+1)%ilosc].acquireUninterruptibly ( ) ;
                    if ( ! (widelec[mojNum].tryAcquire ( ) ) ) {
                        widelec[(mojNum+1)%ilosc].release ( ) ;
                        proby++;
                    } else {
                        podnioslDwaWidelce = true ;
                    }
                }
            } while ( podnioslDwaWidelce == false ) ;
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
        System.out.println("Filozof " + mojNum + " juz najedzony, nie mogl dostac drugiego widelca " + proby + " razy");
    }

}
