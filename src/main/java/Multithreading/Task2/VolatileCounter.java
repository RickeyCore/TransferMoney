package Multithreading.Task2;

public class VolatileCounter {
    private volatile int counter=0;
    public int getCounter() {
        return counter;
    }

    public void increment(){
        counter++;
    }
}
