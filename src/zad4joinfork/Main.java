package zad4joinfork;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int[] integers = new int[]{3,5,1,2,4};
        mergesort mergesort = new mergesort(integers);
        int[] sorted = forkJoinPool.invoke(mergesort);
        System.out.println(Arrays.toString(sorted));
    }
}
