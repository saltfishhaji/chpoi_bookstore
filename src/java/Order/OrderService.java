/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;


import java.util.List;
import Order.Order.Order;
import ShoppingCart.ShoppingCartDao;
import ShoppingCart.ShoppingCart.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;  
/**
 *
 * @author Administrator
 */
public class OrderService {
    public OrderDao dao;
    public ShoppingCartDao shoppingcartdao;
    public void setDao(OrderDao dao){this.dao=dao;}
    public void setShoppingcartdao(ShoppingCartDao shoppingcartdao){this.shoppingcartdao=shoppingcartdao;}
    public List<Order> getOrderByOrderid(Integer orderid){return dao.getOrderByOrderid(orderid);}
    public List<Order> getOrderByUserid(Integer userid){return dao.getOrderByUserid(userid);}
    public boolean updateOrder(Order temp)
    {
        System.out.println("service");
        ShoppingCart cart = new ShoppingCart(temp.getCartid(),temp.getBookid(),temp.getUserid(),temp.getUsername(),temp.getBookname(),-(temp.getAmount()),temp.getPrice()); 
        shoppingcartdao.updateCart(cart);
        return dao.updateOrder(temp);
    }
}
