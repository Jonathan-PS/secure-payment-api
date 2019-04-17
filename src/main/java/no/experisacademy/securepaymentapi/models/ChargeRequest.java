package no.experisacademy.securepaymentapi.models;


import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        nok, eur, usd;
    }

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String token;

    public ChargeRequest() {
    }


    public ChargeRequest(int amount, Currency currency, String token) {
        this.amount = amount;
        this.currency = currency;
        this.token = token;
    }
    public ChargeRequest(String description, int amount, Currency currency, String stripeEmail, String token) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.stripeEmail = stripeEmail;
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}