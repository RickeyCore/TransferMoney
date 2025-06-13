package Multithreading.Task1.Task5;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

/*
Написать программу, которая вычисляет сумму элементов большого массива, разделив работу между несколькими потоками.

**Доп усложнение для разработчиков “мидлов”:** Реализовать через ForkJoinPool и сравнить производительность

**Требования:**

- Создать массив из 10 000 000 случайных целых чисел
- Разделить массив на несколько равных частей (по числу доступных процессоров)
- Каждый поток должен вычислить сумму своей части
- Главный поток должен собрать результаты и вывести общую сумму
- Сравнить время выполнения с однопоточным решением
 */
public class Demo {
    public static void main(String[] args) {
        int[] array = new int[1_000_000_000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }


        // Измерение времени для однопоточного решения
        long start = System.currentTimeMillis();
        long singleThreadSum = sumSequentially(array);
        long singleThreadTime = System.currentTimeMillis() - start;

        System.out.println("Последовательное выполнение: " + singleThreadSum);
        System.out.println("Последовательное выполнение: " + singleThreadTime + " ms");

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        start = System.currentTimeMillis();
        long forkThreadSum =  forkJoinPool.invoke(new SumTask(array, 0, array.length));
        long forkThreadTime = System.currentTimeMillis() - start;

        forkJoinPool.shutdown();
        System.out.println("Параллельное выполнение: " + forkThreadSum);
        System.out.println("Параллельное выполнение: " + forkThreadTime + " ms");


    }

    static long sumSequentially(int[] array) {
        long res = 0;
        for (int i : array) {
            res += i;
        }
        return res;
    }
}

class SumTask extends RecursiveTask<Long> {

    private final int[] array;
    private final int start, end;

    @Override
    protected Long compute() {
        if ( end - start <= 100_000) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        }

        int middle = (start + end) / 2;

        SumTask left = new SumTask(array, start, middle);
        SumTask right = new SumTask(array, middle, end);

        left.fork();
        return right.compute() + left.join();
    }

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
}
