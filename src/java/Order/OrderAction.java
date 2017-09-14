/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;
import Order.Order.Order;
import java.sql.Timestamp;
import Book.Book.Book;
import JMS.QueueSender;
import User.User.User;
import ShoppingCart.ShoppingCart.ShoppingCart;
import ShoppingCart.ShoppingCartService;
import User.UserService;
import com.opensymphony.xwork2.ActionSupport;
import static java.lang.Integer.parseInt;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.servlet.http.HttpSession;
import JMS.QueueSender;
import javax.jms.Destination;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */

public class OrderAction extends ActionSupport {
    private OrderService service;
//    private static ShoppingCartService shoppingcartservice;
    public HttpServletRequest request;
    private Destination destination;
    private QueueSender queuesender;
    public void setDestination(Destination destination){this.destination=destination;}
    public void setQueuesender(QueueSender queuesender){this.queuesender=queuesender;}
    public void setService( OrderService service){this.service=service;}
    public OrderAction(){}
    
    public String getOrderByUserid()
    {
        //int userid = Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("userid")));
        request = ServletActionContext.getRequest();
        User user = new User();
        user=(User)request.getSession().getAttribute("User");
        List<Order> orders = service.getOrderByUserid(user.getUserid());
        //ServletActionContext.getRequest().setAttribute("myorder", orders);
        request.getSession().setAttribute("myorder",orders);
        return SUCCESS;
    }
    
    public String Jump_Order()
    {
        request = ServletActionContext.getRequest();
        User user = (User)request.getSession().getAttribute("User");
        //Integer userid = Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("userid")));
        List<Order> Orders = service.getOrderByUserid(user.getUserid());
        ServletActionContext.getRequest().setAttribute("My_Order", Orders);
        String form = ServletActionContext.getRequest().getParameter("form");
        return SUCCESS;
    }
    
    public String update_Order() //加订单
    {
        System.out.println("hehe0");
        request = ServletActionContext.getRequest();
        User user=new User();
        user=(User)request.getSession().getAttribute("User");
        System.out.println("hehe1");
        System.out.println(user.getUserid());
        System.out.println("hehe2");

        Timestamp ts = new Timestamp(System.currentTimeMillis()); 
        System.out.println("jbdxbl");
        Order temp2 = new Order(
            Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("cartid"))),
            Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("cartid"))),
            Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("bookid"))),
            user.getUserid(),
            user.getUsername(),
            ServletActionContext.getRequest().getParameter("bookname"),
            Integer.parseInt(String.valueOf(ServletActionContext.getRequest().getParameter("amount"))),
            Double.parseDouble(String.valueOf(ServletActionContext.getRequest().getParameter("price"))),
            ts
        );
        
        queuesender.send(destination,temp2);
        //service.updateOrder(temp2);

//        ShoppingCart cart = new ShoppingCart(
//            temp2.getCartid(),
//            temp2.getBookid(),
//            temp2.getUserid(),
//            temp2.getUsername(),
//            temp2.getBookname(),
//            -(temp2.getAmount()),
//            temp2.getPrice()
//        );
//            
//        shoppingcartservice.updateCart(cart);
        return SUCCESS;
    }
}
