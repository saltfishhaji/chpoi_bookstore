/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;
import java.util.Map;
import java.util.HashMap;
import org.hibernate.Query;
import org.hibernate.Session;
import ShoppingCart.ShoppingCart.ShoppingCart;
import java.util.List;
import Book.Book.Book;
import org.hibernate.SessionFactory;
import javax.ejb.Stateful;
        
/**
 *
 * @author Administrator
 */
@Stateful
public class ShoppingCartService {
    public ShoppingCartDao dao;
    public void setDao(ShoppingCartDao dao){this.dao=dao;}
    public List<ShoppingCart> getCartByCartid(Integer cartid){return dao.getCartByCartid(cartid);}
    public List<ShoppingCart> getCartByUserid(Integer userid){return dao.getCartByUserid(userid);}
    public boolean updateCart(ShoppingCart temp){return dao.updateCart(temp);}
}
