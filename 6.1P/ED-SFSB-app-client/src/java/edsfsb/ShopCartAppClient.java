/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edsfsb;

import dto.CartItem;
import ed.sfsb.ShopCartBeanRemote;
import java.util.ArrayList;
import javax.ejb.EJB;

/**
 *
 * @author Tom
 */
public class ShopCartAppClient {

    @EJB
    private static ShopCartBeanRemote shopCart;

    public ShopCartAppClient() {
    }

    public static void main(String[] args) {
        ShopCartAppClient appClient = new ShopCartAppClient();
        // show that the shopCart is empty
        appClient.displayCart();
        // assuming they are selected by the user
        CartItem item1 = new CartItem("000001", "Intel Core i7 CPU", 349.99, 1);
        CartItem item2 = new CartItem("000002", "Intel SSD 512GB", 299.99, 1);

        // Add to cart Test
        System.out.println("\nStart OF addToCart TESTS\n");
        // Increments quantity on duplicate

        System.out.println("RETURNS TRUE AFTER ADD:");

        Boolean result = true;
        for (int i = 0; i < 5; i++) {
            Boolean res = shopCart.addCartItem(item1);
            if (!res) {
                result = false;
            }
        }

        if (result) {
            System.out.println("SUCCESS: addToCart returns true!");
        } else {
            System.out.println("FAILURE: addToCart returns false!");
        }

        ArrayList cart = shopCart.getCart();
        Integer index = cart.indexOf(item1);

        System.out.println("ITEM EXISTS AFTER INSERT:");

        if (index != -1) {
            System.out.println("SUCCESS: Item exists in cart after add!");
            CartItem updatedItem = (CartItem) cart.get(index);
            if (updatedItem.getQuantity() == 5) {
                System.out.println("SUCCESS: Quantity updated after insert!");
            } else {
                System.out.println("FAILURE: Quantity not updated correctly!");
            }
        } else {
            System.out.println("Failure: Item does not exist after add!");
        }

        System.out.println("\nEND OF addToCart TESTS\n");

        System.out.println("\nStart OF deleteFromCart TESTS\n");

        System.out.println("ITEM DOES NOT EXIST AFTER DELETE");
        // Remove the item
        result = shopCart.deleteCartItem(item1.getItemId());
        
        cart = shopCart.getCart();
        index = cart.indexOf(item1);
       
        if (index == -1 && result) {
            System.out.println("SUCCESS: Item does not exist after delete!");
        } else {
            System.out.println("FAILURE: Item exists after delete!");
        }
        
        System.out.println("RETURNS FALSE IF ITEM DOES NOT EXIST:");
        result = shopCart.deleteCartItem(item1.getItemId());
        
        if (!result) {
            System.out.println("SUCCESS: delete returns false if item does not exist!");
        } else {
            System.out.println("FAILURE: delete returns true if item does not exist!");
        }

        System.out.println("\nEND OF deleteFromCart TESTS\n");
        
        System.out.println("\nSTART OF updateCartItem TESTS\n");
        System.out.println("UPDATE ITEM DETAILS:");
        shopCart.addCartItem(item2);
        CartItem updatedItem = new CartItem(item2.getItemId(), "updated",9.9, 4);
        
        result = shopCart.updateCartItem(updatedItem);
        
        if (result) {
            System.out.println("SUCCESS: Returned true on successful update!");
            cart = shopCart.getCart();
            index = cart.indexOf(item2);
            CartItem updateResult = (CartItem) cart.get(index);
            
            if (updateResult.getDescription().equals(updatedItem.getDescription())
                    && updateResult.getQuantity()== updatedItem.getQuantity()
                    && updateResult.getUnitPrice() == updatedItem.getUnitPrice()) {
                System.out.print("SUCCESS: Updated cart item successfully!");
            }
            else {
                System.out.print("FAILURE: Unable to update cart item!");
            }
        } else {
            System.out.println("FAILURE: Returned false on update!");
        }
        
        System.out.println("RETURNS FALSE UPDATING NONEXISTENT ITEM: w");
        result = shopCart.updateCartItem(item1);
        
        if (!result) {
            System.out.println("SUCCESS: Returned false updating item that does not exist");
        } else {
            System.out.println("FAILURE: Returned true updating item that does not exist");
        }
        
        System.out.println("\nEND OF updateCartItem TESTS\n");
    }

    public void addCart(CartItem item) {
        System.out.println("Adding item " + item.getDescription() + " to cart");
        if (shopCart.addCartItem(item)) {
            System.out.println("Your order of " + item.getQuantity()
                    + " " + item.getDescription() + " has been added.");
        } else {
            System.out.println("Sorry, your order of " + item.getQuantity() + " "
                    + item.getDescription() + " cannot be added due to low stock.");
        }
    }

    public void displayCart() {
        ArrayList<CartItem> ciList = shopCart.getCart();
        if (ciList.isEmpty()) {
            System.out.println("The shopping cart is empty!");
            return;
        }
        System.out.println("Your shopping cart has the following items:");
        double total = 0.0;
        for (CartItem ci : ciList) {
            double unitPrice = ci.getUnitPrice();
            double quantity = ci.getQuantity();
            double subTotal = unitPrice * quantity;
            System.out.println("Item: " + ci.getDescription()
                    + "\tUnit Price: " + ci.getUnitPrice()
                    + "\tQuantity: " + ci.getQuantity()
                    + "\tSub-Total: " + subTotal);
            total = total + subTotal;
        }
        System.out.println("---");
        System.out.println("Total price: " + total);
        System.out.println("----End of Shopping Cart---");
    }
}
