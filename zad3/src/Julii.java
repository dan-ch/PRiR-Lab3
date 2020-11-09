import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Julii extends Thread {
    static final Scanner scan = new Scanner(System.in).useLocale(Locale.US);
    final static int N = 4096;
    static int CUTOFF=0;
    static double CI;
    static double CR;
    static int watki;
    static int potega=0;
    static int[][] set = new int[N][N];
    int me;
    public Julii(int me) {
        this.me = me;
    }
    public void run() {
        int begin = 0, end = 0;
        if (me == 0) {
            begin = 0;
            end = (N / 4) * 1;
        }
        else if (me == 1) {
            begin = (N / watki) * 1;
            end = (N / watki) * 2;
        }
        else if (me == 2) {
            begin = (N / watki) * 2;
            end = (N / watki) * 3;
        }
        else if (me == 3) {
            begin = (N / watki) * 3;
            end = (N / watki) * 4;
        }
        else if (me == 4) {
            begin = (N / watki) * 4;
            end = (N / watki) * 5;
        }
        else if (me == 5) {
            begin = (N / watki) * 5;
            end = (N / watki) * 6;
        }
        else if (me == 6) {
            begin = (N / watki) * 6;
            end = (N / watki) * 7;
        }
        else if (me == 7) {
            begin = (N / watki) * 7;
            end = N;
        }
        for (int i = begin; i < end; i++) {
            for (int j = 0; j < N; j++) {
                double zr = (3 * i - 1.5 * N) / N ;
                double zi = (2.5 * j - 1.25 * N) / N;

                int k = 0;
                while (k < CUTOFF && zr * zr + zi * zi < 4.0) {
                    double resr =zr;
                    double resi =zi;
                    for(int z=1; z<potega;z++){
                        double tmpr = resr*zr-resi*zi;
                        double tmpi=resr*zi+resi*zr;
                        resr=tmpr;
                        resi=tmpi;
                    }
                    zr = resr+CR;
                    zi = resi+CI;
                    k++;
                }
                set[i][j] = k;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        int w=0; //watki
        while(w!=2 && w!=4 && w!=6 && w!=8){
            System.out.print("Podaj ilosc watkow (2,4,6,8): ");
            w=scan.nextInt();
        }
        Julii[] watki = new Julii[w];
        Julii.watki=w;
        while(Julii.CUTOFF < 10 || Julii.CUTOFF > 200){
            System.out.print("Podaj cutof (od 10 do 200): ");
            Julii.CUTOFF=scan.nextInt();
        }
        int pot=0;
        while(pot < 2 || pot > 10){
            System.out.print("Podaj potege (od 2 do 10): ");
            pot=scan.nextInt();
        }
        Julii.potega=pot;
        System.out.print("Podaj Cr:");
        Julii.CR = scan.nextDouble();
        System.out.print("Podaj Ci:");
        Julii.CI = scan.nextDouble();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i<w; i++){
            watki[i]=new Julii(i);
            watki[i].start();
        }
        for(int i = 0; i<w; i++){
            watki[i].join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Obliczenia zakończone w czasie " + (endTime - startTime) + " millisekund");
        BufferedImage img = new BufferedImage(N, N, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = set[i][j];
                float level;
                if (k < CUTOFF) {
                    level = (float) k / CUTOFF;
                } else {
                    level = 0;
                }
                Color c = new Color(level/5, level/2, level/3);
                img.setRGB(i, j, c.getRGB());
            }
        }
        ImageIO.write(img, "PNG", new File("Julii.png"));
    }
}

