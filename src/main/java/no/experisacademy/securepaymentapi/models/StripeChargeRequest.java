package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "description")
    private String description;

    @Column(name = "last4")
    private String last4;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "stripe_charge_id")
    private String stripeChargeId;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "stripe_status")
    private String stripeStatus;

    @Column(name = "paid")
    private Boolean paid;

    @Column(name = "outcome_network_status")
    private String outcomeNetworkStatus;

    @Column(name = "outcome_risk_level")
    private String outcomeRiskLevel;

    @Column(name = "outcome_risk_score")
    private Long outcomeRiskScore;

    @Column(name = "outcome_seller_message")
    private String outcomeSellerMessage;

    @Column(name = "outcome_type")
    private String outcomeType;

    @Column(name = "is_active")
    private boolean isActive;


    public StripeChargeRequest() {
    }


    public StripeChargeRequest(Integer userOrderId, String currency, Integer amount, String receiptEmail, String token, String description, String last4, Date createdAt, String stripeChargeId, String receiptUrl, String stripeStatus, Boolean paid, String outcomeNetworkStatus, String outcomeRiskLevel, Long outcomeRiskScore, String outcomeSellerMessage, String outcomeType, boolean isActive) {
        this.userOrderId = userOrderId;
        this.currency = currency;
        this.amount = amount;
        this.receiptEmail = receiptEmail;
        this.token = token;
        this.description = description;
        this.last4 = last4;
        this.createdAt = createdAt;
        this.stripeChargeId = stripeChargeId;
        this.receiptUrl = receiptUrl;
        this.stripeStatus = stripeStatus;
        this.paid = paid;
        this.outcomeNetworkStatus = outcomeNetworkStatus;
        this.outcomeRiskLevel = outcomeRiskLevel;
        this.outcomeRiskScore = outcomeRiskScore;
        this.outcomeSellerMessage = outcomeSellerMessage;
        this.outcomeType = outcomeType;
        this.isActive = isActive;
    }

    public StripeChargeRequest(Integer userOrderId, String currency, Integer amount, String receiptEmail, String token, String description, Date createdAt, String stripeChargeId, String receiptUrl, String stripeStatus, Boolean paid, String outcomeNetworkStatus, String outcomeRiskLevel, Long outcomeRiskScore, String outcomeSellerMessage, String outcomeType, boolean isActive) {
        this.userOrderId = userOrderId;
        this.currency = currency;
        this.amount = amount;
        this.receiptEmail = receiptEmail;
        this.token = token;
        this.description = description;
        this.createdAt = createdAt;
        this.stripeChargeId = stripeChargeId;
        this.receiptUrl = receiptUrl;
        this.stripeStatus = stripeStatus;
        this.paid = paid;
        this.outcomeNetworkStatus = outcomeNetworkStatus;
        this.outcomeRiskLevel = outcomeRiskLevel;
        this.outcomeRiskScore = outcomeRiskScore;
        this.outcomeSellerMessage = outcomeSellerMessage;
        this.outcomeType = outcomeType;
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

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getOutcomeNetworkStatus() {
        return outcomeNetworkStatus;
    }

    public void setOutcomeNetworkStatus(String outcomeNetworkStatus) {
        this.outcomeNetworkStatus = outcomeNetworkStatus;
    }

    public String getOutcomeRiskLevel() {
        return outcomeRiskLevel;
    }

    public void setOutcomeRiskLevel(String outcomeRiskLevel) {
        this.outcomeRiskLevel = outcomeRiskLevel;
    }

    public Long getOutcomeRiskScore() {
        return outcomeRiskScore;
    }

    public void setOutcomeRiskScore(Long outcomeRiskScore) {
        this.outcomeRiskScore = outcomeRiskScore;
    }

    public String getOutcomeSellerMessage() {
        return outcomeSellerMessage;
    }

    public void setOutcomeSellerMessage(String outcomeSellerMessage) {
        this.outcomeSellerMessage = outcomeSellerMessage;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(String outcomeType) {
        this.outcomeType = outcomeType;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}