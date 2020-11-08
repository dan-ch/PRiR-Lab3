import java.util.ArrayList;
import java.util.Random;

public class Main {
    static final Random rand = new Random();
    static int iloscPortow = rand.nextInt(2)+2;
    static int iloscStatkow = 6;
    static int iloscDokow = 1;
    static int kolejny = iloscPortow*iloscStatkow;
    static ArrayList<Port> porty = new ArrayList<>();
    public static Port zmienPort(){
        return porty.get(rand.nextInt(iloscPortow));
    }
    public static void main(String[] args) {
        for(int i=0;i<iloscPortow;i++) {
            porty.add(new Port(iloscDokow, iloscStatkow, i));
            for (int j = 0; j < iloscStatkow; j++) {
                switch (rand.nextInt(2)) {
                    case 0:
                        new Rejsowy(i*iloscPortow+j, 2000, porty.get(i)).start();
                        break;
                    case 1:
                        new Towarowy(i*iloscPortow+j, 10000, porty.get(i)).start();
                        break;
                }

            }
        }
    }
}
