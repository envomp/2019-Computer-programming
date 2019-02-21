package ee.taltech.iti0202.bankmanagement.card;
import ee.taltech.iti0202.bankmanagement.bank.Bank;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.math.BigDecimal;

public abstract class BankCard {
    public Person owner;
    public Bank banker;
    public CardType cardType;

    public enum CardType {
        CREDIT, DEBIT
    }

    /**
     * Constructor factory. Return a CreditCard or DebitCard object according to parameter cardType.
     *
     * @param cardType Specifies objected type to be returned.
     * @param bank     Specifies the bank of the created card.
     * @param person   Specifies the card owner.
     * @return
     */
    public static BankCard createCard(CardType cardType, Bank bank, Person person) {
        if (person.getBankCard().isPresent()) {
            person.getBankCard().get().getBank().removeCustomer(person);
        }

        if (cardType == CardType.DEBIT) {
            BankCard card = new DebitCard(bank, person);
            bank.addCustomer(person);
            person.setBankCard(card);
            return card;
        } else {
            BankCard card = new CreditCard(bank, person);
            bank.addCustomer(person);
            person.setBankCard(card);
            return card;
        }
    }

    public abstract void setOwner(Person owner);

    public abstract void setBanker(Bank banker);

    /**
     * Withdraw the given amount from the card. Abstract function - implemented in subclasses CreditCard and DebitCard.
     *
     * @param value Value to be withdrawn.
     * @return Amount withdrawn.
     * @throws TransactionException Thrown if given value cannot be withdrawn for
     *                              various reasons - specified in subclasses.
     */
    public abstract BigDecimal withdraw(BigDecimal value) throws TransactionException;

    public abstract void deposit(BigDecimal value) throws TransactionException;

    public abstract void setCardType();

    public abstract CardType getCardType();

    public Bank getBank() {
        return this.banker;
    }

    public abstract BigDecimal getBalance();

    public Person getPerson() {
        return this.owner;
    }

}
