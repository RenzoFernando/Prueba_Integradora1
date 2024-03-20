package co.icesi.edu.model;

public abstract class TarjetaPago {
    protected PaymentType paymentType;

    protected TarjetaPago(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    // Abstract method
    public abstract String toString();

}
