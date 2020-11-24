package Java8Study.lambda1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Collections;
import java.util.List;


public class TestSort {
    @Test
    public void test1(){
        List<Integer> list1 = Arrays.asList(1,-2,6,-3,4,67,1,-3,6,37,3);
        Collections.sort(list1, (x,y)-> (int) (Math.pow(x,2) - Math.pow(y,2)));
        list1.forEach(System.out::println);
    }

}
