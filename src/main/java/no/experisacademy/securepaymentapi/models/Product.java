package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "price_each")
    private Double priceEach;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_digital")
    private boolean isDigital;

    public Product(){

    }

    public Product(Long productId, String productName, String description, Double priceEach, Integer stock, String imageUrl, Boolean isActive, Boolean isDigital) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.priceEach = priceEach;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.isActive = isActive;
        this.isDigital = isDigital;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(Double priceEach) {
        this.priceEach = priceEach;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDigital() {
        return isDigital;
    }

    public void setDigital(boolean digital) {
        isDigital = digital;
    }
}
