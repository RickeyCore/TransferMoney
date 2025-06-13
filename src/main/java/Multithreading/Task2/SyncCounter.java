package Multithreading.Task2;

public class SyncCounter {
    public int getCounter() {
        return counter;
    }

    private int counter = 0;
    public void increment(){
        synchronized (this){
            counter++;
        }
    }
}
