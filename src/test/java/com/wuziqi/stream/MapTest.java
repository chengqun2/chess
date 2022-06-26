package com.wuziqi.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapTest {
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> collect = list.stream().map(x -> x * 2).collect(Collectors.toList());
        list.stream().forEach(x -> System.out.print(x+" "));
        System.out.println();
        collect.stream().forEach(x -> System.out.print(x+" "));
    }
}
