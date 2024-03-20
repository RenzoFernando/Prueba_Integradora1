package co.icesi.edu.model;



public class DebitPayment extends TarjetaPago {
    private String cardNumber;
    private String securityCodeCVV;

    public DebitPayment(String cardNumber, String securityCodeCVV) {
        super(PaymentType.DEBIT);
        this.cardNumber = cardNumber;
        this.securityCodeCVV = securityCodeCVV;
    }

    @Override
    public String toString() {
        return "Debit Payment: Card Number - " + cardNumber + ", CVV - " + securityCodeCVV;
    }
}

