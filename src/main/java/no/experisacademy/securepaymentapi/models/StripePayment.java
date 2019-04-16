package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stripe_transaction")
public class StripePayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stripe_transaction_id")
    private long stripeTransactionId;

    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "email")
    private String email;

    /*@Column(name = "private_key")
    private String privateKey;*/

    @Column(name= "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    public StripePayment() {
    }

    public StripePayment(long stripeTransactionId, String tokenId, String email, double amount, String currency) {
        this.stripeTransactionId = stripeTransactionId;
        this.tokenId = tokenId;
        this.email = email;
        this.amount = amount;
        this.currency = currency;
    }

    public long getStripeTransactionId() {
        return stripeTransactionId;
    }

    public void setStripeTransactionId(long stripeTransactionId) {
        this.stripeTransactionId = stripeTransactionId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
