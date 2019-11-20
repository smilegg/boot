package top.djpzh.atomic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.djpzh.atomic.model.User;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
class AtomicApplicationTests {

    @Test
    public void contextLoads() throws InterruptedException {

        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<String,AtomicInteger>();
        AtomicInteger integer = new AtomicInteger(1);
        map.put("key", integer);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                //System.err.println("Thread--"+Thread.currentThread().getId()+Thread.currentThread().getName());
                map.get("key").incrementAndGet();
            });
            //System.err.println("key--"+map.get("key"));
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();

    }

    @Test
    public void contextLoads2() throws InterruptedException {

        //ConcurrentHashMap<String, AtomicReference<User>> map = new ConcurrentHashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user=new User();
        user.setCount(atomicInteger.get());
        atomicReference.getAndSet(user);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                //System.err.println("Thread--"+Thread.currentThread().getId()+Thread.currentThread().getName());
                user.setCount(atomicInteger.incrementAndGet());
                atomicReference.getAndSet(user);
            });
            //System.err.println("key--"+map.get("key"));
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + atomicReference.get().getCount() + "------");
        executorService.shutdown();

    }
}
