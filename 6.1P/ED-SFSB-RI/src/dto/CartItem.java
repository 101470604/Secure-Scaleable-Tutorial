/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Tom
 */
public class CartItem implements Serializable {

    private String itemId;
    private String description;
    private double unitPrice;
    private int quantity;

    public CartItem(String id, String desc, double price, int qty) {
        itemId = id;
        description = desc;
        unitPrice = price;
        quantity = qty;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            CartItem cartItem = (CartItem) obj;
            return (cartItem.itemId.equals(this.itemId));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.itemId);
        return hash;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
