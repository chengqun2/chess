package com.wuziqi;

import com.wuziqi.controller.vm.ChessPointVM;
import com.wuziqi.model.Chess;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JavaBasicTests {

    @Test
    public void test_object_equals(){
        ChessPointVM c1 = new ChessPointVM(1,2);
        ChessPointVM c2 = new ChessPointVM(1,2);
        System.out.println(c1.hashCode());
        System.out.println(c2.hashCode());
        //c1和c2的hashcode相同，但是依然不==
        System.out.println(c1 == c2);
        System.out.println(c1.equals(c2));
    }
}
