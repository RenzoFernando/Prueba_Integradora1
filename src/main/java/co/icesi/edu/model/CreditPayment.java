package co.icesi.edu.model;


public class CreditPayment extends TarjetaPago {
    private String cardNumber;
    private String securityCodeCVV;
    private int installments;

    public CreditPayment(String cardNumber, String securityCodeCVV, int installments) {
        super(PaymentType.CREDIT);
        this.cardNumber = cardNumber;
        this.securityCodeCVV = securityCodeCVV;
        this.installments = installments;
    }

    @Override
    public String toString() {
        return "Credit Payment: Card Number - " + cardNumber + ", CVV - " + securityCodeCVV +
                ", Installments - " + installments;
    }
}
