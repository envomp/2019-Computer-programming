package ee.taltech.iti0202;

import java.util.Random;

public class ClientBuilder {

    private Integer clientAge;
    private Integer clientPhoneNumber;
    private String clientName;

    public Client createClient() {
        return new Client(new Random().nextInt(10000000), clientAge, clientPhoneNumber, clientName);
    }

    public ClientBuilder setClientPhoneNumber(Integer clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
        return this;
    }

    public ClientBuilder setClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public ClientBuilder setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
        return this;
    }


}