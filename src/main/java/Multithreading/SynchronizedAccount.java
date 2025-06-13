package Multithreading;

import java.math.BigDecimal;
import java.util.Objects;

public class SynchronizedAccount {
    private final Long id;
    private volatile BigDecimal balance;

    public SynchronizedAccount(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public synchronized void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SynchronizedAccount synchronizedAccount = (SynchronizedAccount) o;
        return Objects.equals(id, synchronizedAccount.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
