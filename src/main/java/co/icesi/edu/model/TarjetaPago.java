package co.icesi.edu.model;

public abstract class TarjetaPago {
    protected PaymentType paymentType;

    protected TarjetaPago(int paymentType) {
        switch (paymentType) {
            case 1:
                this.paymentType = PaymentType.CREDIT;
                break;
            case 2:
                this.paymentType = PaymentType.DEBIT;
                break;
            case 3:
                this.paymentType = PaymentType.PSE;
                break;
        }
    }

    // Abstract method
    public abstract String toString();

}
