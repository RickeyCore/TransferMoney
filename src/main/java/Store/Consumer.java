package Store;

public class Consumer implements Runnable {
    Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            storage.get();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
