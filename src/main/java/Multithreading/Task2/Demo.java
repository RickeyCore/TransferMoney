package Multithreading.Task2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
**Задача:** Создать программу, которая инкрементирует счетчик из нескольких потоков.
* Затем усовершенствовать решение, чтобы предотвратить состояние гонки.

**Дополнительное задание:** Использовать различные механизмы синхронизации
* (volatile, atomic, locks) и сравнить производительность

**Требования:**

- Создать класс с полем-счетчиком
- Создать и запустить 10 потоков, каждый из которых увеличивает счетчик 1000 раз
- Вывести итоговое значение счетчика (должно быть 10000)
- Сделать два варианта решения: без синхронизации и с синхронизацией
 */
public class Demo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Counter counter = new Counter();
        AtomicCounter atomicCounter = new AtomicCounter();
        SyncCounter syncCounter = new SyncCounter();
        VolatileCounter volatileCounter = new VolatileCounter();
        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(()->{
                    for (int j = 0; j <1_000 ; j++) {
                        counter.increment();
                        atomicCounter.increment();
                        syncCounter.increment();
                        volatileCounter.increment();
                    }
                });
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
        System.out.println("Результат без синхронизации: "+counter.getCounter());
        System.out.println("Результат  синхронизации через атомик: "+atomicCounter.getNumber());
        System.out.println("Результат  синхронизации через synchronized: "+syncCounter.getCounter());
        System.out.println("Результат  синхронизации через volatile: "+volatileCounter.getCounter());
    }
}
