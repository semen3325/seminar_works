package seminar2;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Singleton {
    private static AtomicReference<Singleton> INSTANCE;
    final int id;
    private static final AtomicInteger couinter = new AtomicInteger(0);

    public int getId() {
        return id;
    }

    private Singleton(int id) {
        this.id = id;
    }

    public static Singleton getSinglton() {
        INSTANCE.compareAndSet(null, new Singleton(couinter.getAndIncrement()));

        return INSTANCE.get();
    }


}
