class M_prostokatow extends Thread {
    public double ai, bi, calka;

    public M_prostokatow(double a, double b) {
        ai = a;
        bi = b;
    }

    public void run() {
        double dx;
        dx = (bi - ai) / (double) 1000;
        calka = 0;
        for (int i = 1; i <= 1000; i++) {
            calka += func(ai + i * dx);
        }
        calka *= dx;
        System.out.println("Zakonczenie obliczen metoda prostokatow");
    }

    private double func(double x) {
        return x * x + 3;
    }
}

class M_trapezow extends Thread {

    public double ai, bi, calka;

    public M_trapezow(double a, double b) {
        ai = a;
        bi = b;
    }

    public void run() {
        double dx;
        dx = (bi - ai) / (double) 1000;
        calka = 0;
        for (int i = 1; i < 1000; i++) {
            calka += func(ai + i * dx);
        }
        calka += (func(ai) + func(bi)) / 2;
        calka *= dx;

        System.out.println("Zakończenie obliczen metoda trapezow");
    }

    private double func(double x) {
        return x * x + 3;
    }
}

class M_simpsona extends Thread {
    public double ai, bi, calka;

    public M_simpsona(double a, double b) {
        ai = a;
        bi = b;
    }

    public void run() {
        double dx, s, x;
        dx = (bi - ai) / (double) 1000;
        calka = 0;
        s = 0;

        for (int i = 1; i < 1000; i++) {
            x = ai + i * dx;
            s += func(x - dx / 2);
            calka += func(x);
        }

        s += func(bi - dx / 2);
        calka = (dx / 6) * (func(ai) + func(bi) + 2 * calka + 4 * s);

        System.out.println("Zakonczenie obliczen metoda Simpsona");
    }

    private double func(double x) {
        return x * x + 3;
    }

}

public class Main {

    public static void main(String[] args) {
        M_prostokatow p1 = new M_prostokatow(2, 3);
        M_trapezow t1 = new M_trapezow(4, 5);
        M_simpsona s1 = new M_simpsona(6, 7);


        p1.start();
        t1.start();
        s1.start();
        try {
            t1.join();
            p1.join();
            s1.join();
        } catch (InterruptedException e) {
        }




        System.out.println("metoda prostokatow = " + p1.calka);
        System.out.println("metoda trapezow = " + t1.calka);
        System.out.println("metoda simpsona = " + s1.calka);

        System.out.println("Suma wynikow = " + (p1.calka + t1.calka + s1.calka));
    }
}