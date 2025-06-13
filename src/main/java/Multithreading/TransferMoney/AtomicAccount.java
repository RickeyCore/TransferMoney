package Multithreading.TransferMoney;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicAccount {
    private final long id;

    private AtomicLong balance;

    public AtomicAccount(long id, long balance) {
        if(balance<0){
            throw new IllegalArgumentException("Начальный баланс не может быть отрицательным!");
        }

        this.id = id;
        this.balance = new AtomicLong(balance);
    }

    public boolean withdraw(long amount){
        if (amount <= 0) return false;

        long currentBalance;
        do {
            currentBalance = balance.get();
            if (currentBalance < amount) {
                return false; // Недостаточно средств
            }
        } while (!balance.compareAndSet(currentBalance, currentBalance - amount));

        return true;
    }


    public boolean deposit(long amount) {
        if (amount <= 0) return false;

        long currentBalance;
        long newBalance;
        do {
            currentBalance = balance.get();
            newBalance = currentBalance + amount;
        } while (!balance.compareAndSet(currentBalance, newBalance));

        return true;
    }



    public long getBalance() {
        return balance.get();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtomicAccount account)) return false;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
