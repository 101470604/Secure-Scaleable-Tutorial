/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.sfsb;

import dto.CartItem;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Tom
 */
@Remote
public interface ShopCartBeanRemote {

    ArrayList getCart();

    Boolean addCartItem(CartItem cartItem);

    Boolean deleteCartItem(String itemId);

    Boolean updateCartItem(CartItem cartItem);
    
}
