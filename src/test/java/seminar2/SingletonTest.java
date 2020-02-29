package seminar2;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SingletonTest {

    @Test
    public void getSingleton() {
        ConcurrentSkipListSet<Integer> listSet = new ConcurrentSkipListSet<Integer>();
        CountDownLatch cdl1 = new CountDownLatch(100);
        CountDownLatch cdl2 = new CountDownLatch(100);
        Executor executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                listSet.add(Singleton.getSinglton().getId());
                cdl1.countDown();
                try {
                    cdl1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Singleton.getSinglton();

            });
        }
        cdl2.countDown();
        try {
            cdl2.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, listSet.size());
    }

}