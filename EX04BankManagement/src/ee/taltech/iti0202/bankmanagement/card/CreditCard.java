package ee.taltech.iti0202.bankmanagement.card;

import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {
    private static BigDecimal balance;
    private final int tenK = 10000;
    CreditCard() {
        balance = BigDecimal.valueOf(tenK);
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0
                && balance.subtract(value).compareTo(BigDecimal.valueOf(-tenK / 2)) > 0) {
            balance = balance.subtract(value);
        } else {
            throw new TransactionException();
        }
        return value;
    }

    @Override
    public BigDecimal getBalance() {
        return balance.max(BigDecimal.ZERO);
    }

    @Override
    public void deposit(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            balance = balance.add(value);
        } else {
            throw new TransactionException();
        }
    }

    public BigDecimal getDebt() {
        return balance.min(BigDecimal.ZERO);
    }
}
