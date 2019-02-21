package ee.taltech.iti0202.bankmanagement.card;

import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {
    private static BigDecimal BALANCE;

    DebitCard() {
        BALANCE = BigDecimal.valueOf(0);
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0 && BALANCE.subtract(value).compareTo(BigDecimal.valueOf(0)) > 0) {
            BALANCE = BALANCE.add(value);
        } else {
            throw new TransactionException();
        }
        return value;
    }

    @Override
    public void deposit(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0) {
            BALANCE = BALANCE.add(value);
        } else {
            throw new TransactionException();
        }
    }

    @Override
    public BigDecimal getBalance() {
        return BALANCE;
    }

}