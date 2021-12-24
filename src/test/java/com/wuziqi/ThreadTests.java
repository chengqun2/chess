package com.wuziqi;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.*;

@SpringBootTest
public class ThreadTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new MyRunnableImpl());

        Future future = executorService.submit(new Callable(){
            public Object call() throws Exception {
                LinkedList list = new LinkedList();
                Map map = new HashMap();
                map.put("code","000");
                list.add(map);
                return list;
            }
        });
        System.out.println("===>"+future.get());
    }

    private class MyRunnableImpl implements Runnable {
        @Override
        public void run() {
            System.out.println("run ..");
        }
    }
}
