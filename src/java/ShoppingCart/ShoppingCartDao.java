/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart;
import Book.Book.Book;
import ShoppingCart.ShoppingCart.ShoppingCart;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
import org.apache.struts2.ServletActionContext;



/**
 *
 * @author Administrator
 */
public class ShoppingCartDao {
    private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    public List<ShoppingCart> getCartByCartid(int cartid)
    {
        Session session = factory.openSession();    
        String hql="from ShoppingCart where cartid=:cartid";
        Query query = session.createQuery(hql);
        query.setInteger("cartid", cartid);
        return query.list();
    }
    
    public List<ShoppingCart> getCartByUserid(int userid)
    {
        Session session = factory.openSession();    
        String hql="from ShoppingCart where userid=:userid";
        Query query = session.createQuery(hql);
        query.setInteger("userid", userid);
        return query.list();
    }
    
    public boolean updateCart(ShoppingCart temp)
    {
        Session session = factory.openSession();
        session.beginTransaction();  
        try
        {
            //session.beginTransaction();
            String hehe="from ShoppingCart where userid=:userid and bookid=:bookid";
            Query hehequery=session.createQuery(hehe);
            hehequery.setInteger("userid",temp.getUserid());
            hehequery.setInteger("bookid",temp.getBookid());
            ShoppingCart number=(ShoppingCart)hehequery.uniqueResult();
            if (number==null)
            {
                number=new ShoppingCart();
                number.setAmount(0);
            }
             System.out.println("mmp0");
            System.out.println(number.getAmount());
             System.out.println("hmmp1");
              System.out.println(temp.getAmount());
            temp.setAmount(number.getAmount()+temp.getAmount());
            if(temp.getAmount()<=0)
            {
                String hql = "delete from bookstore.ShoppingCart where userid="+temp.getUserid()+" and bookid="+temp.getBookid();
                System.out.println("sql="+hql);
                Query query = session.createSQLQuery(hql);
                query.executeUpdate();
            }
            else if (number.getAmount()==0)
            {
                 String hql="insert into bookstore.ShoppingCart(bookid,userid,bookname,username,amount,price) values("+temp.getBookid()+","+temp.getUserid()+",'"+temp.getBookname()+"','"+temp.getUsername()+"',"+temp.getAmount()+","+temp.getPrice()+")";
                  System.out.println("sql="+hql);
                 Query query = session.createSQLQuery(hql);
                 query.executeUpdate();
            }
            else
            {
                String hql = "update bookstore.ShoppingCart set amount="+temp.getAmount()+" where userid="+temp.getUserid()+ " and bookid="+temp.getBookid();
                System.out.println("sql="+hql);
                Query query = session.createSQLQuery(hql);
                query.executeUpdate();
            }
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
