package zad4joinfork;
import java.util.Arrays;
import java.util.concurrent.*;
class mergesort extends RecursiveTask<int[]> {

    final private int THRESHOLD = 100;
  int[] arrayToDivide;

  public mergesort(int[] arrayToDivide) {
    this.arrayToDivide = arrayToDivide;

  }

  @Override
  protected int[] compute() {

      int n = arrayToDivide.length;
      if (n < 2) {
          return arrayToDivide;
      } else {
          int mid = n / 2;
          mergesort left = new mergesort(Arrays.copyOfRange(arrayToDivide,0,mid-1));
          mergesort right = new mergesort(Arrays.copyOfRange(arrayToDivide,mid,n));
          left.fork();
          right.fork();
          int[] leftres = left.join();
          int[] rightres= left.join();
          scal_tab(leftres,rightres,arrayToDivide);
          return arrayToDivide;
      }
  }




  private void scal_tab(
          int[] tab1,
          int[] tab2,
          int[] scal_tab) {

    int i = 0, j = 0, k = 0;

    while ((i < tab1.length) && (j < tab2.length)) {

      if (tab1[i] < tab2[j]) {
        scal_tab[k] = tab1[i++];
      } else {
        scal_tab[k] = tab2[j++];
      }

      k++;
    }

    if (i == tab1.length) {

      for (int a = j; a < tab2.length; a++) {
        scal_tab[k++] = tab2[a];
      }

    } else {

      for (int a = i; a < tab1.length; a++) {
        scal_tab[k++] = tab1[a];
      }

    }
  }

}
