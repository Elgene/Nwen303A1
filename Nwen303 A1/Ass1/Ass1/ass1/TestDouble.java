package ass1;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestDouble {
    public static final Double[][] dataset={
            {0.2,0.3,0.4,0.5,0.6,0.7},
            {0.8,0.9,1.0,1.1,1.2,1.3},
            {-0.7,1.6,-0.5,-4.0,3.0,-2.0},
            {7.7,-6.6,5.5,4.4,3.3,2.2},
            {8.0,8.5,9.0,9.5,1.7,-5.5,4.5,3.5,2.5},
            {},
            manyOrdered(10000),
            manyReverse(10000),
            manyRandom(10000)
    };
    static private Double[] manyRandom(int size) {
        Random r=new Random(0);
        Double[] result=new Double[size];
        for(int i=0;i<size;i++){result[i]=r.nextDouble();}
        return result;
    }
    static private Double[] manyReverse(int size) {
        Double[] result=new Double[size];
        for(int i=0;i<size;i++){result[i]=(size-i)+0.42;}
        return result;
    }
    static private Double[] manyOrdered(int size) {
        Double[] result=new Double[size];
        for(int i=0;i<size;i++){result[i]=i+0.42;}
        return result;
    }

    @Test
    public void testISequentialSorter() {
        Sorter s=new ISequentialSorter();
        for(Double[]l:dataset){TestHelper.testData(l,s);}
    }

    @Test
    public void testMSequentialSorter() {
        Sorter s=new MSequentialSorter();
        for(Double[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter1() {
        Sorter s=new MParallelSorter1();
        for(Double[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter2() {
        Sorter s=new MParallelSorter2();
        for(Double[]l:dataset){TestHelper.testData(l,s);}
    }
    @Test
    public void testMParallelSorter3() {
        Sorter s=new MParallelSorter3();
        for(Double[]l:dataset){TestHelper.testData(l,s);}
    }
}
