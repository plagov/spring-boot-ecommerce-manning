package com.cakefactory.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "item")
public class ItemEntity {

    @Id
    private String sku;
    private String title;
    private BigDecimal price;

    public ItemEntity(String sku, String title, BigDecimal price) {
        this.sku = sku;
        this.title = title;
        this.price = price;
    }

    public ItemEntity() {

    }

    public String getSku() {
        return sku;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
