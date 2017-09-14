/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jaas;

/**
 *
 * @author Administrator
 */
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import User.UserDao;
import User.User.User;
import Jaas.UserPrincipal;
import Jaas.SimplePrincipal;


public class MyLoginModule implements LoginModule 
{
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> options;
    private static UserDao userdao;
    private UserPrincipal userPrincipal;
    private SimplePrincipal simplePrincipal;
    private boolean pass;
    public void setUserdao(UserDao userdao){this.userdao=userdao;}
    @Override
    public boolean abort() throws LoginException {return true;}
    @Override
    public boolean logout() throws LoginException 
    {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(simplePrincipal);
        return true;
    }
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) 
    {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
         try 
        {
            NameCallback nameCallback=new NameCallback("username");
            PasswordCallback passwordCallback=new PasswordCallback("password",false);
            final Callback[] callbacks=new Callback[]{nameCallback,passwordCallback};
            callbackHandler.handle(callbacks);
            String username=nameCallback.getName();
            String password=String.valueOf(passwordCallback.getPassword());
            if(username.equals("user123")&&password.equals("user123"))
            {
                userPrincipal=new UserPrincipal(username);
                simplePrincipal=new SimplePrincipal("manager");
                pass=true;
                return true;
            }
            User user=new User();
            user.setUsername(username);
            user.setPassword(password);
            if(userdao.UserLogin(user))
            {
                userPrincipal=new UserPrincipal(username);
                simplePrincipal=new SimplePrincipal("user");
                pass=true;
                return true;
            }
        }
        catch (UnsupportedCallbackException e)
        {
            LoginException e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        }
        catch (IOException e)
        {
            LoginException e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        }
        return true;
    }
    @Override
    public boolean commit() throws LoginException 
    {
        if(pass)
        {
            this.subject.getPrincipals().add(userPrincipal);
            this.subject.getPrincipals().add(simplePrincipal);
            return pass;
        }
        //return false;
        else
        {
             throw new LoginException("Authentication Failure");   
        }   
    }
}

