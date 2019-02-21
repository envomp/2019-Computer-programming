
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

        BankCard peepDebitCard = BankCard.createCard(BankCard.CardType.CREDIT, SEB, peep);

        try {
            System.out.println(peepDebitCard.getBalance());
            peepDebitCard.deposit(BigDecimal.TEN);
            System.out.println(peepDebitCard.withdraw(BigDecimal.valueOf(15009)));
            System.out.println(peepDebitCard.getBalance());
            System.out.println(SEB.getRichestCustomerByGender(Person.Gender.MALE));
        } catch (TransactionException e){

        }


        // ...a


    }
}
