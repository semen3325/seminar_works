package seminar2;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SingletonTest {
    private static final int nTreads = 1000;

    @Test
    public void getSingleton() {
        ConcurrentSkipListSet<Integer> listSet = new ConcurrentSkipListSet<Integer>();
        CountDownLatch startCdl = new CountDownLatch(nTreads);
        CountDownLatch endCdl = new CountDownLatch(nTreads);
        Executor executor = Executors.newFixedThreadPool(nTreads);
        for (int i = 0; i < nTreads; i++) {
            executor.execute(() -> {
                try {
                    startCdl.await();
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    fail();
                }
                Singleton singleton = Singleton.getSinglton();
                listSet.add(singleton.getId());
                endCdl.countDown();
            });
            startCdl.countDown();
        }
        try {
            endCdl.await();
        } catch (InterruptedException e) {
            //e.printStackTrace();
            fail();
        }

        assertEquals(1, listSet.size());
    }

}