import java.util.Random;

public class Rejsowy extends Statek{
    static int TANKUJ = 2000;
    static int REZERWA = 500;

    public Rejsowy(int numer, int paliwo, Port p){
        this.paliwo=paliwo;
        this.numer=numer;
        this.port=p;
        this.stan=REJS;
        rand = new Random();
    }

    @Override
    public void zatankuj() {
        paliwo = TANKUJ;
    }

    @Override
    public void odbierzPaliwo() {
        paliwo -= (rand.nextInt(300)+300);
    }

    @Override
    public boolean czyRezerwa() {
        return paliwo<REZERWA;
    }

    @Override
    public String ladowanie() {
        return " ludzie wsiadaja na poklad";
    }

    @Override
    public String rozladowanie() {
        return " ludzie opuszczaja poklad";
    }
}
