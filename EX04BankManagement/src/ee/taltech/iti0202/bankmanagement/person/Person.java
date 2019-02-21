package ee.taltech.iti0202.bankmanagement.person;

import ee.taltech.iti0202.bankmanagement.card.BankCard;

import java.util.Optional;

public class Person {

    private String personFirstName;
    private String personLastName;
    private Integer personAge;
    private double personMonthlyIncome;
    private Gender genderbender;
    private Optional<BankCard> card = Optional.empty();

    public enum Gender {MALE, FEMALE}


    public Person(String firstName, String lastName, int age, Gender gender, double monthlyIncome) {
        this.personAge = age;
        this.personFirstName = firstName;
        this.personLastName = lastName;
        this.personMonthlyIncome = monthlyIncome;
        this.genderbender = gender;

    }

    public String getFirstName() {
        return this.personFirstName;
    }

    public String getLastName() {
        return this.personLastName;
    }

    public int getAge() {
        return this.personAge;
    }

    public Gender getGender() {
        return this.genderbender;
    }

    public double getMonthlyIncome() {
        return personMonthlyIncome;
    }

    /**
     * Return Optional.empty() if person has no bankcard.
     *
     * @return Optional of BankCard
     */
    public Optional<BankCard> getBankCard() {
        return card;
    }

    public void setBankCard(BankCard bankCard) {
        card = Optional.of(bankCard);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.personFirstName, this.personLastName);
    }
}