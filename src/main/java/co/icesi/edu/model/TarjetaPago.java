package co.icesi.edu.model;

public class TarjetaPago {
    protected PaymentType paymentType;
    protected String resumen;

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



}
