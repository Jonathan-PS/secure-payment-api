package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "UserOrder")
public class UserOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long userOrderId;

    @Column(name = "registered_user_id")
    private long registeredUserId;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_email")
    private String shippingEmail;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "stripe_transaction_id")
    private long stripeTransactionId;

    @Column(name = "is_active")
    private boolean isActive;

    public UserOrder(){

    }



    public UserOrder(long userOrderId, long registeredUserId, String shippingName, String shippingAddress, String shippingEmail,String status, boolean isActive) {
        this.userOrderId = userOrderId;
        this.registeredUserId = registeredUserId;
        this.shippingName = shippingName;
        this.shippingAddress = shippingAddress;
        this.shippingEmail = shippingEmail;
        this.status = status;
        this.isActive = isActive;
    }

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(long registeredUserId) {
        this.registeredUserId = registeredUserId;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingEmail() {
        return shippingEmail;
    }

    public void setShippingEmail(String shippingEmail) {
        this.shippingEmail = shippingEmail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getStripeTransactionId() {
        return stripeTransactionId;
    }

    public void setStripeTransactionId(long stripeTransactionId) {
        this.stripeTransactionId = stripeTransactionId;
    }
}
