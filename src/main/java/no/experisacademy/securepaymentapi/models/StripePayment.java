package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stripe_transaction")
public class StripePayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stripe_transaction_id")
    private Long stripeTransactionId;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    /*@Column(name = "private_key")
    private String privateKey;*/

    @Column(name= "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    public StripePayment() {
    }

    public StripePayment(long stripeTransactionId, String token, String email, double amount, String currency) {
        this.stripeTransactionId = stripeTransactionId;
        this.token = token;
        this.email = email;
        this.amount = amount;
        this.currency = currency;
    }

    public Long getStripeTransactionId() {
        return stripeTransactionId;
    }

    public void setStripeTransactionId(Long stripeTransactionId) {
        this.stripeTransactionId = stripeTransactionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
