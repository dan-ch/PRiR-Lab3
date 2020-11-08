import java.util.Random;

public class Towarowy extends Statek{
    static int TANKUJ = 10000;
    static int REZERWA = 1000;

    public Towarowy(int numer, int paliwo, Port p){
        this.paliwo=paliwo;
        this.numer=numer;
        this.port=p;
        this.stan=REJS;
        rand = new Random();
    }

    @Override
    public void zatankuj() {
        paliwo=TANKUJ;
    }

    @Override
    public void odbierzPaliwo() {
        paliwo -= (rand.nextInt(1500)+500);
    }

    @Override
    public boolean czyRezerwa() {
        return paliwo<REZERWA;
    }

    @Override
    public String ladowanie() {
        return " towar jest ladowany na poklad";
    }

    @Override
    public String rozladowanie() {
        return " towar jest zdejmowany z pokladu";
    }
}
