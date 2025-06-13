package Store;

public class Producer implements Runnable {
    Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            storage.put();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
