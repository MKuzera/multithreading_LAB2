package zad3zFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    static final int THREADS_NUMBER  = 5;
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        int zadania = 20;
        double start = 0;
        double end = Math.PI;
        double zakres = (end-start)/zadania;
        int tasks = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);



        List<Future<Double>> wyniki = new ArrayList<>();

        //Future zawiera wynik ktory moze byc obliczony w tle (synchronicznie)
        //czyli lista z wynikami ktore beda dodawane na biezaco


        // WARIANT 3 Exec Service z Future

        System.out.println("Podaj DX");
        Double dx = Double.valueOf(scanner.next());

        Future<Double>wynik = null;

        try {

            for (int i = 0; i < zadania; i++) {
                Calka_callable calka = new Calka_callable(start + zakres * i, start + zakres * (i + 1), dx); // tworzy obiekt Callable (tylko callable lub runnable moze byc w exec service)
                wynik = executorService.submit(calka);
                wyniki.add(wynik);
            }

            executorService.shutdown();


        } finally {

        }


        while (!executorService.isTerminated()) {}

        System.out.println("Finished all threads");

       Double wynikDouble = 0.0;
        for (Future<Double> doubleFuture : wyniki) {
            try {
                wynikDouble+=doubleFuture.get();


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Wynik koncowy " + wynikDouble );




        // Callable ma call() i zwraca okreslny typ
        // Runnable ma run() void

        // Runable prostszy
        // Callable pozwala na obsluge wyjatkow oraz wynikow . stosowany z executorService


    }
}