package ee.taltech.iti0202.bankmanagement;

import ee.taltech.iti0202.bankmanagement.bank.Bank;
import ee.taltech.iti0202.bankmanagement.card.BankCard;
import ee.taltech.iti0202.bankmanagement.exceptions.TransactionException;
import ee.taltech.iti0202.bankmanagement.person.Person;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Bank SEB = new Bank("SEB");
        Bank LHV = new Bank("LHV");
        Bank swedbank = new Bank("Swedbank");

        Person peep = new Person("Peep", "Tamm", 30, Person.Gender.MALE, 1600);
        Person anne = new Person("Anne", "Kask", 27, Person.Gender.FEMALE, 2000);
        Person valdo = new Person("Valdo", "Sepp", 56, Person.Gender.MALE, 1300);
        Person malle = new Person("Malle", "Kuusk", 69, Person.Gender.FEMALE, 600);
        Person juulius = new Person("Juulius", "Tipikas", 19, Person.Gender.MALE, 400);

        System.out.println("- - - - DEBIT - - - -");


        System.out.println(peep.getBankCard().isEmpty()); // true

        BankCard peepDebitCard = BankCard.createCard(BankCard.CardType.DEBIT, SEB, peep);

        System.out.println(peep.getBankCard().isEmpty()); // false
        System.out.println(peepDebitCard == peep.getBankCard().get()); // true
        System.out.println(SEB.getCustomers().contains(peep)); // true

        boolean exceptionThrown = false;
        try {
            peepDebitCard.deposit(BigDecimal.TEN);
        } catch (TransactionException e) {
            exceptionThrown = true;
        }

        System.out.println(exceptionThrown); // false
        System.out.println(peepDebitCard.getBalance().equals(BigDecimal.TEN)); // true

        exceptionThrown = false;
        try {
            peepDebitCard.withdraw(BigDecimal.valueOf(11));
        } catch (TransactionException e) {
            exceptionThrown = true;
        }

        System.out.println(exceptionThrown); // true
        System.out.println(peepDebitCard.getBalance().equals(BigDecimal.TEN)); // true


        System.out.println("- - - - CREDIT - - - -");

        BankCard peepCreditCard = BankCard.createCard(BankCard.CardType.CREDIT, LHV, peep);

        // ...


    }
}
