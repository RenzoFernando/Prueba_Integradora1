package co.icesi.edu.model;

public class PSEPayment extends TarjetaPago {
    private String bankName;


    public PSEPayment(String bankName) {
        super(3);
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "PSE Payment: Bank Name - " + bankName;
    }
}
