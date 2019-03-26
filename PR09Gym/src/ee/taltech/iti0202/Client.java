package ee.taltech.iti0202;


public class Client {

    private Integer clientAge;
    private Integer clientPhoneNumber;
    private String clientName;
    private Integer cliendID;

    public Client(Integer cliendID, Integer clientAge, Integer clientPhoneNumber, String clientName) {
        this.cliendID = cliendID;
        this.clientAge = clientAge;
        this.clientPhoneNumber = clientPhoneNumber;
        this.clientName = clientName;

    }

    public Integer getClientAge() {
        return clientAge;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }

    public Integer getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(Integer clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getCliendID() {
        return cliendID;
    }

    @Override
    public String toString() {
        return String.format("ID: %7s\tName: %10s\tAge: %3s\tPhone number: %,11d\n", cliendID, clientName, clientAge, clientPhoneNumber);
    }
}
