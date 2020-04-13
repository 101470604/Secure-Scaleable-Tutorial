/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Tom
 */
@Stateful
public class ShopCartBean implements ShopCartBeanRemote {

    private ArrayList<CartItem> cart;

    // Constructor
    public ShopCartBean() {
        cart = new ArrayList<>();
    }

    // Destructor
        @Remove
    public void remove() {
        cart = null;
    }

    // CART METHODS
    private Boolean add(CartItem cartItem) {
        boolean result = false;
        try {
            result = cart.add(cartItem);
        } catch (Exception ex) {
            System.err.println("Error adding item to cart!");
        }
        return result;
    }

    @Override
    public ArrayList getCart() {
        return cart;
    }

    @Override
    public Boolean addCartItem(CartItem cartItem) {
        try {
            if (cartItem == null) {
                throw new NullPointerException("addCartItem: cartItem was null");
            } else {
                Integer index = cart.indexOf(cartItem);
                // If cart contains the itemm
                if (index != -1) {
                    // Increment the quantity
                    CartItem item = cart.get(index);
                    item.setQuantity(item.getQuantity() + 1);
                } else {
                    // Add new item to cartd
                    if (!add(cartItem)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (NullPointerException nullEx) {
            nullEx.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteCartItem(String itemId) {
        return cart.remove(new CartItem(itemId, "", 0, 0));

    }
    
    @Override
    public Boolean updateCartItem(CartItem cartItem) {
        Integer index = cart.indexOf(cartItem);
        if (index != -1) {
            cart.set(index, cartItem);
            return true;
        } 
        return false;
    }
    
    

}
