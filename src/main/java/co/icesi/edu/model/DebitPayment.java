package co.icesi.edu.model;



public class DebitPayment extends TarjetaPago {
    private String cardNumber;
    private String securityCodeCVV;

    public DebitPayment(String cardNumber, String securityCodeCVV) {
        super(2);
        this.cardNumber = cardNumber;
        this.securityCodeCVV = securityCodeCVV;
    }

    @Override
    public String toString() {
        return super.toString() + "Debit Payment: Card Number - " + cardNumber + ", CVV - " + securityCodeCVV;
    }


}

