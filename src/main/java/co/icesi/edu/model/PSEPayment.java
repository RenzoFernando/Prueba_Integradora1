package co.icesi.edu.model;

public class PSEPayment extends TarjetaPago {
    private String bankName;
    private String phoneNumber;

    public PSEPayment(String bankName, String phoneNumber) {
        super(PaymentType.PSE);
        this.bankName = bankName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PSE Payment: Bank Name - " + bankName + ", Phone Number - " + phoneNumber;
    }
}
