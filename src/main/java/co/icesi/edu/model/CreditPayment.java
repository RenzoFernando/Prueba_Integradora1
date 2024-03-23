package co.icesi.edu.model;


public class CreditPayment extends TarjetaPago {
    private String cardNumber;
    private String securityCodeCVV;
    private int installments;



    public CreditPayment(String cardNumber, String securityCodeCVV, int installments) {
        super(1);
        this.cardNumber = cardNumber;
        this.securityCodeCVV = securityCodeCVV;
        this.installments = installments;
        super.resumen = toString();
    }

    @Override
    public String toString() {
        return  "Credit Payment: Card Number - " + cardNumber + ", CVV - " + securityCodeCVV +
                ", Installments - " + installments;
    }


}



