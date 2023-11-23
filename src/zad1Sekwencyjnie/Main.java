package zad1Sekwencyjnie;


import java.util.Scanner;

public class Main {

    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        double start = 0;
        double end = Math.PI;
        double wynik = 0.0;


        // WARIANT 1 sekwencyjny
        System.out.println("Podaj DX");
        Double dx = Double.valueOf(scanner.next());
        Calka_callable calka = new Calka_callable(start ,end,dx);
        wynik = calka.compute_integral();
        System.out.println("wynik sekwencyjnie: " + wynik);


    }
}