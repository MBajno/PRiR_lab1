public class Zjazd {
    static int ZJAZD_NA_PRZYSTANEK = 1;
    static int START = 2;
    static int ODJAZD_Z_PRZYSTANKU = 4;
    int ilosc_przystankow;
    int ilosc_zajetych_przystankow;
    int ilosc_autobusow;

    Zjazd(int ilosc_przystankow, int ilosc_autobusow) {
        this.ilosc_przystankow = ilosc_przystankow;
        this.ilosc_autobusow = ilosc_autobusow;
        this.ilosc_zajetych_przystankow = 0;
    }

    synchronized int start(int numer) {
        ilosc_zajetych_przystankow--;
        System.out.println("Pozwolenie na rozpoczecie zjazdu na przystanek " + numer);
        return START;
    }

    synchronized int zjazd() {
        try {
            Thread.currentThread().sleep(1000);
        } catch (Exception ie) {
        }
        if (ilosc_zajetych_przystankow < ilosc_przystankow) {
            ilosc_zajetych_przystankow++;
            System.out.println("Pozwolenie na zjazd na miejsce " + ilosc_zajetych_przystankow);
            return ZJAZD_NA_PRZYSTANEK;
        } else {
            return ODJAZD_Z_PRZYSTANKU;
        }
    }

    synchronized void zmniejsz() {
        ilosc_autobusow--;
        System.out.println("SKONCZYLO SIE PALIWO");
        if (ilosc_autobusow == ilosc_przystankow) System.out.println("Ilosc autobusow jaka sama jak przystankow");
    }
}