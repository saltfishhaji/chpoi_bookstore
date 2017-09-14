/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order;
import Order.Order.Order;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Administrator
 */
@TransactionAttribute(NOT_SUPPORTED)
@Stateless
public class OrderDao{
    private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    public List<Order> getOrderByOrderid(int orderid)
    {
        Session session = factory.openSession();    
        String hql="from Order where orderid=:orderid";
        Query query = session.createQuery(hql);
        query.setInteger("orderid", orderid);
        return query.list();
    }
    public List<Order> getOrderByUserid(int userid)
    {
        Session session = factory.openSession();    
        String hql="from Order where userid=:userid";
        Query query = session.createQuery(hql);
        query.setInteger("userid", userid);
        return query.list();
    }
    
    @TransactionAttribute(REQUIRES_NEW)
    public boolean updateOrder(Order temp)
    {
        System.out.println("dao");
        Session session = factory.openSession();
        try
        {
            session.beginTransaction();  
            String hql = "insert into bookstore.Order(orderid,cartid,bookid," +
            "userid,bookname,username,amount,price,time) values(" +
            temp.getOrderid() + "," + temp.getCartid() + "," +
            temp.getBookid() + "," + temp.getUserid() + ",'" +
            temp.getBookname() + "','" + temp.getUsername() + "'," +
            temp.getAmount() + "," + temp.getPrice() + ",'" +temp.getTime() + "')";
            Query query = session.createSQLQuery(hql);
            query.executeUpdate();

            
            //session.save(temp);
            session.getTransaction().commit();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
                return false;         
        }
        return true;
    }
}
