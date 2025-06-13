package Multithreading.TransferMoney;


import java.math.BigDecimal;
import java.util.logging.Logger;


/*
 **Условие: нужно реализовать сервис, который будет отправлять средства с одного счета на другой.
 * Двумя способами: через пессимистичную стратегию блокировок (через блокировки) и
 * оптимистичиный подход (lock-free) — без блокировок:**


 **Также бонусный вопрос такой**: подумай в каком случае нужно применить пессемистичный подход ? А Оптимистичный?
 * Какие подходы и паттерны можно использовать для организации отказоустойчивости?
 */
public class TransferMoney {
    private final static Logger logger = Logger.getLogger(TransferMoney.class.getSimpleName());

    static boolean transferMoney(SynchronizedAccount from, SynchronizedAccount to, BigDecimal amount) {
        if (!validate(from, to, amount)) {
            logger.info("Данные не прошли проверку!");
            return false;
        }

        SynchronizedAccount firstLock = from.hashCode() > to.hashCode() ? from : to;
        SynchronizedAccount secondLock = to.hashCode() > from.hashCode() ? to : from;
        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance().compareTo(amount) >= 0) {
                    logger.info("Выполняется перевод денежных средств.");
                    from.setBalance(from.getBalance().subtract(amount));
                    to.setBalance(to.getBalance().add(amount));
                    return true;
                }
            }
        }
        logger.info("Недостаточный баланс на счёте");
        return false;
    }

    static boolean transferMoney(AtomicAccount from, AtomicAccount to, long amount) {
        if (!validate(from, to, amount)) {
            logger.info("Данные не прошли проверку!");
            return false;
        }

        if (from.getBalance() >= amount) {
            logger.info("Выполняется перевод денежных средств.");

            if (!from.withdraw(amount)) {
                logger.info("Неудалось списать деньги со счёта");
                return false;
            }
            if (!to.deposit(amount)) {
                for (int i = 0; i < 5; i++) {
                    if(from.deposit(amount)){
                        break;
                    };
                    logger.info("Попытка отката транзакции");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                logger.info("Неудалось выполнить депозит на аккаунт");
                return false;
            }

            return true;
        }
        logger.info("Недостаточный баланс на счёте");
        return false;
    }

    private static boolean validate(SynchronizedAccount from, SynchronizedAccount to, BigDecimal amount) {
        return from != null &&
                to != null &&
                amount != null &&
                amount.compareTo(BigDecimal.ZERO) > 0 &&
                !from.equals(to);
    }

    private static boolean validate(AtomicAccount from, AtomicAccount to, long amount) {
        return from != null &&
                to != null &&
                amount > 0 &&
                !from.equals(to);
    }
}
