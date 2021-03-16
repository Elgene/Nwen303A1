package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * I learned that ForkJoinPools are designed for divide and conquer recursive problems like our Merge sort algorithm.
 * Our merge sort algorithm is symmetrical as it divides the problem in two, however during runtime the threads
 * can take more or less time for one branch than the other side. The ForkJoinPool helps the slower branch to catch
 * up by releasing resources from the completed threads and giving them to threads that need them. The ForkJoinPool
 * also seems safer as it doesn't require us to use futures, so there is no need to try Future.get() and do something
 * when catching InterruptedExceptions or ExecutionExceptions.
 * */
public class MParallelSorter3 implements Sorter {

  static final ForkJoinPool mainPool = new ForkJoinPool();

  @Override
  public <T extends Comparable<? super T>> List<T> sort(List<T> list) {
    if(list.size() < 20) return new ISequentialSorter().sort(list);
    class Sort extends RecursiveTask<List<T>>{

      List<T> list;

      Sort(List<T> l){
        list = l;
      }

      @Override
      protected List<T> compute() {
        int middle = list.size() / 2;
        List<T> left = list.subList(0, middle);
        List<T> right = list.subList(middle, list.size());
        return merge(sort(left), sort(right));
      }}
    return mainPool.invoke(new Sort(list));



  }

  private <T extends Comparable<? super T>> List<T> merge(List<T> a, List<T> b) {
    List<T> merged = new ArrayList<T>();
    int leftPointer = 0, rightPointer = 0; //counters of items left in respective lists
    while(leftPointer < a.size() || rightPointer < b.size()) {
      if(leftPointer < a.size() && rightPointer < b.size()) {
        if(a.get(leftPointer).compareTo(b.get(rightPointer)) <= 0.0) {
          merged.add(a.get(leftPointer));
          leftPointer++;
        } else {
          merged.add(b.get(rightPointer));
          rightPointer++;
        }
      } else if(leftPointer+1 <= a.size()) {
        merged.add(a.get(leftPointer));
        leftPointer++;
      } else if(rightPointer+1 <= b.size()) {
        merged.add(b.get(rightPointer));
        rightPointer++;
      }
    }
    return merged;
  }

}
