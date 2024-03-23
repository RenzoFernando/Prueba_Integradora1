package co.icesi.edu.model;



public class DebitPayment extends TarjetaPago {
    private String cardNumber;
    private String securityCodeCVV;

    public DebitPayment(String cardNumber, String securityCodeCVV) {
        super(2);
        this.cardNumber = cardNumber;
        this.securityCodeCVV = securityCodeCVV;
        super.resumen= toString();
    }

    @Override
    public String toString() {
        return "Debit Payment: Card Number - " + cardNumber + ", CVV - " + securityCodeCVV;
    }


}

