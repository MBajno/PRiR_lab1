public class Main_Autobusy {
    static int ilosc_autobusow = 10;
    static int ilosc_przystankow = 5;
    static Zjazd zjazd;

    public static void main(String[] args) {
        zjazd = new Zjazd(ilosc_autobusow, ilosc_przystankow);
        for (int i = 0; i < ilosc_autobusow; i++)
            new Autobus(i, 1000, zjazd).start();
    }
}