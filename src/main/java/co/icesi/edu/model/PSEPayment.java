package co.icesi.edu.model;

public class PSEPayment extends TarjetaPago {
    private String bankName;


    public PSEPayment(String bankName) {
        super(3);
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return super.toString() + "PSE Payment: Bank Name - " + bankName;
    }
}
