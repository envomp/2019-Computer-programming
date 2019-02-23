package ee.taltech.iti0202.bankmanagement.card;
import ee.taltech.iti0202.bankmanagement.bank.Bank;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.math.BigDecimal;

public final class CreditCard extends BankCard {
    private static BigDecimal balance;
    private final int tenK = 10000;
    CreditCard(Bank bank, Person person) {
        balance = BigDecimal.valueOf(tenK);
        setBanker(bank);
        setOwner(person);
        setCardType();
    }

    @Override
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public void setBanker(Bank banker) {
        this.banker = banker;
    }

    @Override
    public BigDecimal withdraw(BigDecimal value) throws TransactionException {
        if (value.compareTo(BigDecimal.ZERO) > 0
                && balance.subtract(value).compareTo(BigDecimal.valueOf(-tenK / 2)) >= 0) {
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

    @Override
    public void setCardType() {
        this.cardType = CardType.CREDIT;
    }

    @Override
    public CardType getCardType() {
        return CardType.CREDIT;
    }

    // must be positive?
    public BigDecimal getDebt() {
        return balance.min(BigDecimal.ZERO).abs();
    }
}
