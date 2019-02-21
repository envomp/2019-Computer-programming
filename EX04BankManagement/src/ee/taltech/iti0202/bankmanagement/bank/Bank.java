package ee.taltech.iti0202.bankmanagement.bank;

import ee.taltech.iti0202.bankmanagement.person.Person;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Bank {

    private Set<Person> customers = new HashSet<>();
    private String name;

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<Person> getCustomers() {
        return customers;
    }

    public Boolean addCustomer(Person person) {
        if (customers.contains(person)) {
            return false;
        }
        customers.add(person);
        return true;
    }

    public Boolean removeCustomer(Person person) {
        if (customers.contains(person)) {
            customers.remove(person);
            return true;
        }
        return false;
    }

    public double getAverageCustomerMonthlyIncome() {
        double average = 0;
        int ppl = 0;
        for (Person person : customers) {
            ppl += 1;
            average += person.getMonthlyIncome();
        }

        if (ppl == 0) {
            return 0;
        }
        return average / ppl;
    }

    public double getAverageCustomerMonthlyIncome(int maxAge) {
        double average = 0;
        int ppl = 0;
        for (Person person : customers) {
            if (person.getAge() <= maxAge) {
                ppl += 1;
                average += person.getMonthlyIncome();
            }
        }

        if (ppl == 0) {
            return 0;
        }
        return average / ppl;
    }

    public double getAverageCustomerMonthlyIncome(int minAge, int maxAge) {
        double average = 0;
        int ppl = 0;
        for (Person person : customers) {
            if (person.getAge() <= maxAge && person.getAge() >= minAge) {
                ppl += 1;
                average += person.getMonthlyIncome();
            }
        }

        if (ppl == 0) {
            return 0;
        }
        return average / ppl;
    }

    public double getAverageCustomerMonthlyIncome(Person.Gender gender) {
        double average = 0;
        int ppl = 0;
        for (Person person : customers) {
            if (person.getGender() == gender) {
                ppl += 1;
                average += person.getMonthlyIncome();
            }
        }

        if (ppl == 0) {
            return 0;
        }
        return average / ppl;
    }

    public Set<Person> getAllCustomersWithCreditCards() {
        Set<Person> cards = new HashSet<>();
        for (Person person : customers) {
            if (person.getBankCard().isPresent()) {


            }
        }

        return cards;
    }

    public Set<Person> getAllCustomersWithDebitCards() {
        return null;
    }

    public Optional<Person> getRichestCustomerByGender(Person.Gender gender) {
        Person rich = null;
        for (Person person : customers) {
            if (person.getGender() == gender) {
                if (rich == null && person.getBankCard().isPresent()) {
                    rich = person;
                } else if
                (person.getBankCard().get().getBalance().compareTo(rich.getBankCard().get().getBalance()) > 0) {
                    rich = person;
                }
            }
        }
        if (rich == null) {
            return Optional.empty();
        }
        return Optional.of(rich);
    }

    @Override
    public String toString() {
        return getName();
    }
}
