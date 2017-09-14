/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book.Book;

import java.io.Serializable;


/**
 *
 * @author Administrator
 */

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer bookid;
    private String bookname;
    private String author;
    private double price;
    private String category;
    public Book() {}
    public Book(Integer bookid) {this.bookid = bookid;}
    public Book(Integer bookid, String bookname, String author, double price,String category) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.category=category;
    }
    public Integer getBookid() {return bookid;}
    public void setBookid(Integer bookid) {this.bookid = bookid;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public String getBookname() {return bookname;}
    public void setBookname(String bookname) {this.bookname = bookname;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
}
