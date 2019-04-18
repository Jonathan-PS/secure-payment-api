package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_order")
public class UserOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long userOrderId;

    @Column(name = "registered_user_id")
    private Long registeredUserId;

    @Column(name = "shipping_name")
    private String shippingName;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "order_email")
    private String orderEmail;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "is_active")
    private boolean isActive;

    public UserOrder(){

    }

    public UserOrder(String shippingName, String shippingAddress, String orderEmail, Date createdAt, Date updatedAt, String status, boolean isActive) {
        this.shippingName = shippingName;
        this.shippingAddress = shippingAddress;
        this.orderEmail = orderEmail;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.isActive = isActive;
    }

    public UserOrder(long userOrderId, Long registeredUserId, String shippingName, String shippingAddress, String orderEmail, String status, boolean isActive) {
        this.userOrderId = userOrderId;
        this.registeredUserId = registeredUserId;
        this.shippingName = shippingName;
        this.shippingAddress = shippingAddress;
        this.orderEmail = orderEmail;
        this.status = status;
        this.isActive = isActive;
    }

    public Long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(Long registeredUserId) {
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

    public String getOrderEmail() {
        return orderEmail;
    }

    public void setOrderEmail(String orderEmail) {
        this.orderEmail = orderEmail;
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
}
