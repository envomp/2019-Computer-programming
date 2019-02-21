package ee.taltech.iti0202.bankmanagement.card;

import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {

    private static BigDecimal BALANCE;

    CreditCard() {
        BALANCE = BigDecimal.valueOf(10000);
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0 && BALANCE.subtract(value).compareTo(BigDecimal.valueOf(-5000)) > 0) {
            BALANCE = BALANCE.subtract(value);
        } else {
            throw new TransactionException();
        }
        return value;
    }

    @Override
    public BigDecimal getBalance() {
        return BALANCE.max(BigDecimal.ZERO);
    }

    @Override
    public void deposit(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            BALANCE = BALANCE.add(value);
        } else {
            throw new TransactionException();
        }
    }

    public BigDecimal getDebt() {
        return BALANCE.min(BigDecimal.ZERO);
    }
}