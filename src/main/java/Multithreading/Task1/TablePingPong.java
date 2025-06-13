package Multithreading.Task1;

/*
Классическая задача с собесов: Создай два потока (threads), которые будут по очереди выводить тексты "ping" и "pong".
 Поток "ping" должен начинать первым, и игра заканчивается после N итераций. (встречал на собеседовании в fiducia)
 */
public class TablePingPong {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong(3);

        Thread ping = new Thread(pingPong::ping);
        Thread pong = new Thread(pingPong::pong);

        ping.start();
        pong.start();
        try {
            ping.join();
            pong.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

class PingPong {
    private final int maxIterations;
    private boolean pingTurn = true;
    private int currentIteration = 0;
    private boolean isFinished = false;

    public PingPong(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public synchronized void ping() {
        while (!isFinished) {
            try {
                while (!pingTurn && !isFinished) {
                    wait();
                }

                System.out.println("Ping");
                currentIteration++;


                if (currentIteration >= maxIterations) {
                    isFinished = true;
                    notifyAll();
                    break;
                }


                pingTurn = false;
                notify();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void pong() {
        while (!isFinished) {
            try {
                while (pingTurn && !isFinished) {
                    wait();
                }

                System.out.println("Pong");
                pingTurn = true;
                notifyAll();

                if (currentIteration >= maxIterations) {
                    isFinished = true;
                    notifyAll();
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

