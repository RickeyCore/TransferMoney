package Multithreading.Task2;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private AtomicInteger number = new AtomicInteger(0);

    public void increment(){
        number.incrementAndGet();
    }

    public int getNumber() {
        return number.get();
    }
}
