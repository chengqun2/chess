package com.wuziqi.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortedTest {
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(1,4,5,2,3,8,7,6);
        List<Integer> collect = list.stream().sorted().collect(Collectors.toList());
        collect.stream().forEach(System.out::print);
        System.out.println();
        List<Integer> collect2= list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        collect2.stream().forEach(System.out::print);
    }
}
