package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;

@Entity
@Table(name = "stripe_charge_request")
public class StripeChargeRequest {

    public enum Currency {
        nok, eur, usd;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long stripe_charge_request_id;

    @Column(name = "user_order_id")
    private Integer userOrderId;

    @Column(name = "currency")
    private String currency;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "receipt_email")
    private String receiptEmail;

    @Column(name = "token")
    private String token;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "stripe_status")
    private String stripeStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "stripe_charge_id")
    private String stripeChargeId;

    @Column(name = "is_active")
    private boolean isActive;


    public StripeChargeRequest() {
    }

    public StripeChargeRequest(Integer userOrderId, String currency, Integer amount, String receiptEmail, String token, String receiptUrl, String stripeStatus, String description ,String stripeChargeId, boolean isActive) {
        this.userOrderId = userOrderId;
        this.currency = currency;
        this.amount = amount;
        this.receiptEmail = receiptEmail;
        this.token = token;
        this.receiptUrl = receiptUrl;
        this.stripeStatus = stripeStatus;
        this.description = description;
        this.stripeChargeId = stripeChargeId;
        this.isActive = isActive;
    }

    public Long getStripe_charge_request_id() {
        return stripe_charge_request_id;
    }

    public void setStripe_charge_request_id(Long stripe_charge_request_id) {
        this.stripe_charge_request_id = stripe_charge_request_id;
    }

    public Integer getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Integer userOrderId) {
        this.userOrderId = userOrderId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReceiptEmail() {
        return receiptEmail;
    }

    public void setReceiptEmail(String receiptEmail) {
        this.receiptEmail = receiptEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }

    public String getStripeStatus() {
        return stripeStatus;
    }

    public void setStripeStatus(String stripeStatus) {
        this.stripeStatus = stripeStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStripeChargeId() {
        return stripeChargeId;
    }

    public void setStripeChargeId(String stripeChargeId) {
        this.stripeChargeId = stripeChargeId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}