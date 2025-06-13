package Multithreading.TransferMoney;

import java.math.BigDecimal;

public class DemoTransferMoney {
    public static void main(String[] args) {

//        SynchronizedAccount synchronizedAccount1 = new SynchronizedAccount(1L, new BigDecimal("1000.00"));
//        SynchronizedAccount synchronizedAccount2 = new SynchronizedAccount(2L, new BigDecimal("500.00"));
//
//        System.out.println("До перевода:");
//        System.out.println("Account 1: " + synchronizedAccount1.getBalance());
//        System.out.println("Account 2: " + synchronizedAccount2.getBalance());
//
//        Thread t1 = new Thread(() -> {
//            TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2,  new BigDecimal("100.00")); // Успех
//        });
//        Thread t2 = new Thread(() -> {
//            TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2, new BigDecimal("2000.00")); // Недостаточно средств
//        });
//        Thread t3 = new Thread(() -> {
//            TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2, new BigDecimal("900.00"));  // Полный баланс
//        });
//        t1.start();
//        t2.start();
//        t3.start();
//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("\nПосле переводов:");
//        System.out.println("Account 1: " + synchronizedAccount1.getBalance());
//        System.out.println("Account 2: " + synchronizedAccount2.getBalance());




        AtomicAccount account1 = new AtomicAccount(1L, 1000L);
        AtomicAccount account2 = new AtomicAccount(2L, 500L);

        System.out.println("До перевода:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());

        Thread t1 = new Thread(() -> {
            TransferMoney.transferMoney(account1, account2, 100L); // Успех
        });
        Thread t2 = new Thread(() -> {
            TransferMoney.transferMoney(account1, account2, 2000L); // Недостаточно средств
        });
        Thread t3 = new Thread(() -> {
            TransferMoney.transferMoney(account1, account2, 900L);  // Полный баланс
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("\nПосле переводов:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());
    }
}
