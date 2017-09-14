/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Book;

import Book.Book.Book;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class BookService {
    private BookDao dao;
    public void setDao(BookDao dao){this.dao = dao;}
    public List<Book> SearchBook(String type, String search){return dao.SearchBook(type, search);}
    public boolean AddBook(Book book){return dao.AddBook(book);}
    public boolean DeleteBook(Book book){return dao.DeleteBook(book);}
}
