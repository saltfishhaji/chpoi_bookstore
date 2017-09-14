/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.WebService;

import Book.Book.Book;
import Book.BookService;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author Administrator
 */
@WebService(endpointInterface="User.WebService.IWebservice", serviceName = "IWebservice")
public class webservice implements IWebservice{
    private static BookService service;
     public void setService(BookService service) {
        webservice.service = service;
    }

    @Override
    public List<Book> Query_books(String type, String search)
    {
        List<Book> books = service.SearchBook(type, search);
        return books;
    }
}
