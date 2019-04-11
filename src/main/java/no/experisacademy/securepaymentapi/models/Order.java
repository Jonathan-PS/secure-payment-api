package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UserOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_order_id")
    private long userOrderId;

    @Column(name = "registered_user_id") // FOREIGN KEY
    private long registeredUserId;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "shipping_email")
    private String shippingEmail;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private String status;

    /*@Column(name = "stripe_transaction_id")
    private long stripeTransactionId;*/

    @Column(name = "is_active")
    private boolean isActive;

    public Order(){

    }

    public Order(long userOrderId, long registeredUserId, String shippingName, String shippingAddress, String shippingEmail, String createdAt, Date updatedAt, String status, boolean isActive) {
        this.userOrderId = userOrderId;
        this.registeredUserId = registeredUserId;
        this.shippingName = shippingName;
        this.shippingAddress = shippingAddress;
        this.shippingEmail = shippingEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
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
}
