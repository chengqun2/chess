package com.wuziqi.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterTest {
    @Test
    public void test(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> collect = list.stream().filter(i -> i > 5).collect(Collectors.toList());
        list.stream().forEach(System.out::print);
        System.out.println();
        collect.stream().forEach(System.out::print);
    }
}
