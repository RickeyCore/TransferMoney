package Multithreading.TransferMoney;

public class DemoTransferMoney {
    public static void main(String[] args) {
        /*
        SynchronizedAccount synchronizedAccount1 = new SynchronizedAccount(1L, new BigDecimal("1000.00"));
        SynchronizedAccount synchronizedAccount2 = new SynchronizedAccount(2L, new BigDecimal("500.00"));

        System.out.println("До перевода:");
        System.out.println("Account 1: " + synchronizedAccount1.getBalance());
        System.out.println("Account 2: " + synchronizedAccount2.getBalance());

        TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2, new BigDecimal("100.00")); // Успех
        TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2, new BigDecimal("2000.00")); // Недостаточно средств
        TransferMoney.transferMoney(synchronizedAccount1, synchronizedAccount2, new BigDecimal("900.00"));  // Полный баланс

        System.out.println("\nПосле переводов:");
        System.out.println("Account 1: " + synchronizedAccount1.getBalance());
        System.out.println("Account 2: " + synchronizedAccount2.getBalance());

         */


        AtomicAccount account1 = new AtomicAccount(1L, 1000L);
        AtomicAccount account2 = new AtomicAccount(2L, 500L);

        System.out.println("До перевода:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());

        TransferMoney.transferMoney(account1, account2, 100L); // Успех
        TransferMoney.transferMoney(account1, account2, 2000L); // Недостаточно средств
        TransferMoney.transferMoney(account1, account2, 900L);  // Полный баланс

        System.out.println("\nПосле переводов:");
        System.out.println("Account 1: " + account1.getBalance());
        System.out.println("Account 2: " + account2.getBalance());
    }
}
