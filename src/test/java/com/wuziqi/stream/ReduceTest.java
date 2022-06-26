package com.wuziqi.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceTest {
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(30,20,10_000);
        Optional<Integer> reduce = list.stream().reduce((a,b) -> a+b);
        System.out.println(reduce.isPresent()==true?reduce.get():" empty ");
    }
}
