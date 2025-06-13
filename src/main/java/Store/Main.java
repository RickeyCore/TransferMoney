package Store;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Storage storage = new Storage();
        Thread t1 = new Thread(new Producer(storage));
        Thread t2 = new Thread(new Consumer(storage));
        t1.start();
        t2.start();
    }
}
