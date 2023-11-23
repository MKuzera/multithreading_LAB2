package zad2ExecService;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static final int THREADS_NUMBER  = 5;
    static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


        double start = 0;
        double end = Math.PI;
        double zakres = (end-start)/THREADS_NUMBER;
        double wynik = 0.0;
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        // tworzy pule o okreslnej liczbie wątkow


        // WARIANT 2 Exec Service

        System.out.println("Podaj DX");
        Double dx = Double.valueOf(scanner.next());
        try {
            for(int i =0;i<THREADS_NUMBER;i++) {
                Calka_callable calka = new Calka_callable(start + zakres * i, start + zakres * (i + 1), dx); // tworzy obiekt Callable (tylko callable lub runnable moze byc w exec service)
                wynik += executorService.submit(() -> calka.compute_integral()).get();
            }// tu dla kazdego watku w exec service (pula watkow) wykonuje obliczenia rownolegle


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

        System.out.println("wynik sekwencyjnie: " + wynik);

        while (!executorService.isTerminated()) {}

        System.out.println("Finished all threads");




        // Callable ma call() i zwraca okreslny typ
        // Runnable ma run() void

        // Runable prostszy
        // Callable pozwala na obsluge wyjatkow oraz wynikow . stosowany z executorService


    }
}