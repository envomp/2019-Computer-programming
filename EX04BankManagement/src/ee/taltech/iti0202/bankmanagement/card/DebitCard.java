
package ee.taltech.iti0202.bankmanagement.card;

import ee.taltech.iti0202.bankmanagement.bank.Bank;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.math.BigDecimal;

public final class DebitCard extends BankCard {
    private static BigDecimal balance;

    DebitCard(Bank bank, Person person) {
        balance = BigDecimal.valueOf(0);
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
        if (value.compareTo(BigDecimal.ZERO) > 0 && balance.subtract(value).compareTo(BigDecimal.valueOf(0)) > 0) {
            balance = balance.add(value);
        } else {
            throw new TransactionException();
        }
        return balance;
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
        this.cardType = CardType.DEBIT;
    }

    @Override
    public CardType getCardType() {
        return CardType.DEBIT;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

}
