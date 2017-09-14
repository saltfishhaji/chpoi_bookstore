/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShoppingCart.ShoppingCart;
//import User.User.User;
//import Book.Book.Book;
import javax.ejb.Stateful;
import java.io.Serializable;
/**
 *
 * @author Administrator
 */
@Stateful
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cartid;
    private Integer bookid;
    private Integer userid;
    private String username;
    private String bookname;
    private Integer amount;
    private double price;
    public ShoppingCart(){};
    public ShoppingCart(Integer cartid,Integer bookid,Integer userid,String username,String bookname,Integer amount, double price)
    {
        this.userid = cartid;
        this.bookid = bookid;
        this.userid = userid;
        this.username = username;
        this.bookname = bookname;
        this.amount = amount;
        this.price = price;
    }
    public Integer getCartid(){return this.cartid;}
    public void setCartid (Integer cartid){this.cartid = cartid;}
    public Integer getBookid(){return this.bookid;}
    public void setBookid (Integer bookid){this.bookid = bookid;}
    public Integer getUserid(){return this.userid;}
    public void setUserid (Integer userid){this.userid = userid;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}
    public String getBookname(){return this.bookname;}
    public void setBookname(String bookname){this.bookname=bookname;}
    
    public Integer getAmount(){return this.amount;}
    public void setAmount(Integer amount){this.amount=amount;}
    public double getPrice(){return this.price;}
    public void setPrice(double price){this.price=price;}
}

/*import User.User.User;
import Book.Book.Book;
import java.util.Map;
import java.util.HashMap;
//import java.util.Set;  
//import java.util.Iterator;
import javax.ejb.Stateful;

@Stateful
public class ShoppingCart {
    private static User user;
    private static Map<Book,Integer> map=new HashMap<Book,Integer>();
    
    public void setUser(User user)
    {
        this.user = user;
    }
    
    public void addCart(Book book,int i)
    {
        if(i<=0)    return;
        //Set<Book> keySet = map.keySet();  
        this.map.put(book,i);
       /* boolean contains = map.containsKey(bookname); 
        if (contains)
        {
            
            map.put(bookname,i+map.get(bookname));       
        }
        else
        {
            map.put(bookname,i);
        }
    }
    
    public void updateCart(Book book,int i)
    {
        if(i>0)
            addCart(book,i);
        else
            deleteCart(book,i);
    }
    
    public void deleteCart(Book book,int i)
    {
        if(i>0) return;
        this.map.remove(book);
    }
    
    public Map<Book,Integer> getMap() 
    { 
        return this.map;
    }
    
    public User getUser()
    {
        return this.user;
    }
}*/
