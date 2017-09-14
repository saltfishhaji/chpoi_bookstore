/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order.Order;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer orderid;
    private Integer cartid;
    private Integer bookid;
    private Integer userid;
    private String username;
    private String bookname;
    private Integer amount;
    private double price;
    private java.sql.Timestamp time;
    public Order(){};
    public Order(Integer orderid, Integer cartid,Integer bookid,Integer userid,String username,String bookname,Integer amount,double price, Timestamp time)
    {
        this.orderid = orderid;
        this.cartid = cartid;
        this.bookid = bookid;
        this.userid = userid;
        this.username = username;
        this.bookname = bookname;
        this.amount = amount;
        this.price = price;
        this.time = time;
    }
    public Integer getOrderid(){return this.orderid;}
    public void setOrderid (Integer orderid){this.orderid = orderid;}
    public Integer getCartid(){return this.cartid;}
    public void setCartid (Integer cartid){this.cartid = cartid;}
    public Integer getBookid(){return this.bookid;}
    public void setBookid (Integer bookid){this.bookid = bookid;}
    public Integer getUserid(){return this.userid;}
    public void setUserid (Integer userid){this.userid = userid;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}
    public String getBookname(){return this.bookname;}
    public void setBookname(String bookname){this.bookname = bookname;}
    
    public Integer getAmount(){return this.amount;}
    public void setAmount(Integer amount){this.amount = amount;}
    public double getPrice(){return this.price;}
    public void setPrice(double price){this.price = price;}
    public Timestamp getTime(){return this.time;}
    public void setTime(Timestamp time){this.time = time;}
}
