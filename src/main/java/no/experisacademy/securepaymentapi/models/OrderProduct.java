package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
public class OrderProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long orderProductId;

    @Column(name = "user_order_id")
    private Long userOrderId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price_each")
    private Double priceEach;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "is_active")
    private boolean isActive;

    private Product product;


    public OrderProduct() {
    }

    public OrderProduct(Long userOrderId, Long productId, Double priceEach, Long quantity, boolean isActive) {
        this.userOrderId = userOrderId;
        this.productId = productId;
        this.priceEach = priceEach;
        this.quantity = quantity;
        this.isActive = isActive;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(Long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Double priceEach) {
        this.priceEach = priceEach;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
