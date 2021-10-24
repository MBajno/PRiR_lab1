import java.util.Random;

public class Autobus extends Thread {
    static int PRZYSTANEK = 1;
    static int START = 2;
    static int JAZDA = 3;
    static int KONIEC_ZJAZDU = 4;
    static int BRAK_PALIWA = 5;
    static int TANKUJ = 1000;
    static int REZERWA = 500;

    int numer;
    int paliwo;
    int stan;
    Zjazd l;
    Random rand;

    public Autobus(int numer, int paliwo, Zjazd l) {
        this.numer = numer;
        this.paliwo = paliwo;
        this.stan = JAZDA;
        this.l = l;
        rand = new Random();
    }

    public void run() {
        while (true) {
            if (stan == PRZYSTANEK) {
                if (rand.nextInt(2) == 1) {
                    stan = START;
                    paliwo = TANKUJ;
                    System.out.println("Na przystanku, szukam miejsca, autobus " + numer);
                    stan = l.start(numer);
                } else {
                    System.out.println("Jeszcze nie bede zjezdzac " + numer);
                }

            } else if (stan == START) {
                System.out.println("Szukam, autobus " + numer);
                stan = JAZDA;

            } else if (stan == JAZDA) {
                paliwo -= rand.nextInt(500);
                System.out.println("Autobus " + numer + " szuka ");
                if (paliwo <= REZERWA) {
                    stan = KONIEC_ZJAZDU;
                } else try {
                    sleep(rand.nextInt(1000));
                } catch (Exception e) {
                }

            } else if (stan == KONIEC_ZJAZDU) {
                System.out.println("Zjechalem na przystanek " + numer + " ilosc paliwa " + paliwo);
                stan = l.zjazd();

                if (stan == KONIEC_ZJAZDU) {
                    paliwo -= rand.nextInt(500);
                    System.out.println("REZERWA " + paliwo);
                    if (paliwo <= 0) stan = BRAK_PALIWA;
                }
            } else if (stan == BRAK_PALIWA) {
                System.out.println("Skonczylo sie paliwo autobus " + numer);
                l.zmniejsz();
            }
        }
    }
}