/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

import Book.Book.Book;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Administrator
 */
public class BookDao {
    private SessionFactory factory;
    public void setFactory(SessionFactory factory){this.factory = factory;}
    public Book getBookById(int id)
    {
        Session session = factory.openSession();
        String hql = "from Book where bookid=:id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        return (Book)query.uniqueResult();
    }
    
    public boolean DeleteBook(Book book)
    {
        Session session = factory.openSession();
        session.beginTransaction();
        String hql = "delete from bookstore.Book where bookid="+book.getBookid();
        Query query = session.createSQLQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
    }
    
    public boolean AddBook(Book book)
    {
        Session session = factory.openSession();
            try{
                session.beginTransaction();
                //String hql1="set names 'utf8'";
                //Query query1 = session.createSQLQuery(hql1);
                String hql="INSERT INTO `book`(bookname,author,price,category) VALUES ('"
                        +book.getBookname()+"','"
                        +book.getAuthor() + "','"
                        +book.getPrice()+ "','"
                        +book.getCategory()+"');";
                //session.save(book);
                Query query1 = session.createSQLQuery(hql);
                query1.executeUpdate();
                session.getTransaction().commit();
            }catch(Exception ex)
            {
                ex.printStackTrace();
                return false;
            }
        return true;
    }
    
    public List<Book> SearchBook(String type, String search)
    {
        Session session = factory.openSession();
        String hql = "from Book where "+type+"=:search";
        Query query = session.createQuery(hql);
        query.setString("search", search);
        query.setCacheable(true);
        return query.list();
    }
}
