/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

//import RMI_Service.IService;
import User.User.User;
import com.opensymphony.xwork2.ActionSupport;
import Jaas.SimpleCallbackHandler;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import User.WebService.IWebservice;


/**
 *
 * @author Administrator
 */
@Aspect
public class UserAction extends ActionSupport {
    private UserService service;
    private User user;
    public HttpServletRequest request;
    public void setService(UserService service){this.service = service;}
    public void setUser(User user){this.user = user;}
    public UserAction() {}
    public String Jump_UserLogin(){
        String role = ServletActionContext.getRequest().getParameter("role");
        ServletActionContext.getRequest().setAttribute("role", role);
        return SUCCESS;
    }
    public String Jump_UserRegister(){return SUCCESS;}
    public String Jump_BookAffair(){return SUCCESS;}
    public String Jump_UserAffair(){return SUCCESS;}
    public String Jump_Manager(){return SUCCESS;}
    public String Jump_Chatroom(){return SUCCESS;}
    public String Set_English()
    {
        ServletActionContext.getRequest().getSession().setAttribute("language","US");
        return SUCCESS;
    }
    public String Set_Chinese()
    {
        ServletActionContext.getRequest().getSession().setAttribute("language","ZN");
        return SUCCESS;
    }
    @Pointcut("execution(* Book.BookAction.Jump_Books())")
    public void Point_Cut(){}
    @Before("Point_Cut()")
    public void Before()
    {
        String username = (String)ServletActionContext.getRequest().getSession().getAttribute("username");
        if(username == null || username.equals("null"))
        {
            ServletActionContext.getRequest().setAttribute("login_flag", "false");
            System.out.println("The user is not login!");
        }
    }
    public String Jump_Index()
    {
        request.getSession().invalidate();
        return SUCCESS;
    }
    public String Jump_Home()
    {
        request = ServletActionContext.getRequest();
        user=(User)request.getSession().getAttribute("User");
        ServletActionContext.getRequest().getSession().setAttribute("User", user);
        return SUCCESS;
    }
    public String UserRegister()
    {
        //User user = new User();
        System.out.println("fuck0");
        user.setUsername(ServletActionContext.getRequest().getParameter("username"));
        user.setPassword(ServletActionContext.getRequest().getParameter("password"));
        System.out.println("fuck1");
        String passwordd = ServletActionContext.getRequest().getParameter("passwordd");
        user.setEmail(ServletActionContext.getRequest().getParameter("email"));
        user.setMobile(ServletActionContext.getRequest().getParameter("mobile"));
        if(!passwordd.equals(user.getPassword()))
            return "passs";
        System.out.println("fuck2");
        System.out.println("fuck3");
        if(service.UserRegister(user))
        {
            user = service.getUserByName(user.getUsername());
            ServletActionContext.getRequest().getSession().setAttribute("User", user);
            return SUCCESS;
            //ServletActionContext.getRequest().getSession().setAttribute("User", user);
        }
        System.out.println("fuck4");
        return ERROR;
    } 
    
    public String Manager_Login()
    {
        user.setUsername(ServletActionContext.getRequest().getParameter("j_username"));
        user.setPassword(ServletActionContext.getRequest().getParameter("j_password"));
        try
        {
            //System.setSecurityManager(new SecurityManager());
            LoginContext context = new LoginContext("Login1",new SimpleCallbackHandler(user.getUsername(),user.getPassword()));
            context.login();
            if(user.getUsername().equals("user123")&&user.getPassword().equals("user123"))    
            {
                user = service.getUserByName(user.getUsername());
                request = ServletActionContext.getRequest();
                request.getSession().setAttribute("User", user);
                return SUCCESS;
            }
        }
        catch (LoginException e)
        {
            e.printStackTrace();
            return ERROR;
        }
        catch(SecurityException e)
        {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String User_Login()
    {
        user.setUsername(ServletActionContext.getRequest().getParameter("username"));
        user.setPassword(ServletActionContext.getRequest().getParameter("password"));

        if(service.UserLogin(user))
        {
            
            user = service.getUserByName(user.getUsername());
            //ServletActionContext.getRequest().setAttribute("User", user);
            
            request = ServletActionContext.getRequest();
            request.getSession().setAttribute("User", user);

            ServletActionContext.getRequest().getSession().setAttribute("username", user.getUsername());
            ServletActionContext.getRequest().getSession().setAttribute("userid", user.getUserid());
            request = ServletActionContext.getRequest();
	    String name = String.valueOf(request.getSession().getAttribute("username"));
            System.out.println(user.getUsername());
            /*ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:User/WebService/cxf.xml");
            IWebservice w_service = (IWebservice)ctx.getBean("WebService_Publish");
            String str=w_service.gettime().toString();
            ServletActionContext.getRequest().getSession().setAttribute("logintime", str);
            System.out.println(str);*/
            return SUCCESS;
            
        }
        return ERROR;
       
    }
    
    public String Search_Users()
    {
        String type = ServletActionContext.getRequest().getParameter("type");
        String search = ServletActionContext.getRequest().getParameter("search");
        List<User> users = service.SearchUsers(type, search);
        ServletActionContext.getRequest().setAttribute("User_Search", users);
        return SUCCESS;
    }
}
