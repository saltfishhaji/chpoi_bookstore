/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;
import Book.Book.Book;
import User.User.User;
import ShoppingCart.ShoppingCart.ShoppingCart;
import User.UserService;
import com.opensymphony.xwork2.ActionSupport;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Administrator
 */
public class ShoppingCartAction extends ActionSupport {
    private ShoppingCartService service;
    public HttpServletRequest request;
    public void setService(ShoppingCartService service){this.service=service;} 
    public ShoppingCartAction(){}
    public String getCartByUserid()
    {
        //int userid = Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("userid")));
        request = ServletActionContext.getRequest();
        User user=new User();
        user=(User)request.getSession().getAttribute("User");
        List<ShoppingCart> carts=service.getCartByUserid(user.getUserid());
        //ServletActionContext.getRequest().setAttribute("mycart", carts);
        request.getSession().setAttribute("mycart",carts);
        return SUCCESS;
    }
    public String update_Cart()
    {
        System.out.println("hehe0");
        request = ServletActionContext.getRequest();
        ShoppingCart temp=new ShoppingCart();
        User user=new User();
        user=(User)request.getSession().getAttribute("User");
        //User user = (User)ServletActionContext.getRequest().getAttribute("User");
        System.out.println("hehe1");
        //temp.setUserid(Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("userid").toString())));
        temp.setUserid(user.getUserid());
        System.out.println(user.getUserid());
        System.out.println("hehe2");
        //temp.setUsername(ServletActionContext.getRequest().getParameter("username"));
        temp.setUsername(user.getUsername());
        System.out.println(user.getUsername());
        temp.setAmount(Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("amount"))));
        temp.setPrice(Double.parseDouble(String.valueOf(ServletActionContext.getRequest().getParameter("price"))));
        temp.setBookid(Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("bookid"))));
        temp.setBookname(ServletActionContext.getRequest().getParameter("bookname"));
        System.out.println("hehe3");
        service.updateCart(temp);
        System.out.println("hehe4");
        return SUCCESS;
    }
    
    public String Jump_ShoppingCart()
    {
        request = ServletActionContext.getRequest();
        User user = (User)request.getSession().getAttribute("User");
        //Integer userid = Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("userid")));
        List<ShoppingCart> ShoppingCarts = service.getCartByUserid(user.getUserid());
        ServletActionContext.getRequest().setAttribute("My_Cart", ShoppingCarts);
        String form = ServletActionContext.getRequest().getParameter("form");
        return SUCCESS;
    }
}
