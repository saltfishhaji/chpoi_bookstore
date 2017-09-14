/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Book.Book.Book;
import User.User.User;
import java.util.List;
import javax.ejb.Stateless;
/**
 *
 * @author Administrator
 */
@Stateless
public class UserService {
    private UserDao dao;
    public void setDao(UserDao dao){this.dao = dao;}
    public User getUserById(int id){return dao.getUserById(id);}
    public User getUserByName(String username){return dao.getUserByName(username);}
    public Boolean UserRegister(User user)
    {
        if(dao.getUserByName(user.getUsername()) != null)   return false;
        if(!dao.UserRegister(user)) return false;
        return true;
    }
    
    public Boolean UserLogin(User user)
    {
        if(dao.getUserByName(user.getUsername()) == null)   return false;
        if(!dao.UserLogin(user))    return false;
        return true;
    }
    
     public List<User> SearchUsers(String type, String search){return dao.SearchUsers(type, search);}
}
