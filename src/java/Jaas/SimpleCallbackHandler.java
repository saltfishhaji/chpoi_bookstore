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
import javax.security.auth.callback.*;
import java.io.IOException;

public class SimpleCallbackHandler implements CallbackHandler 
{
    private String username;
    private char[] password;
    public SimpleCallbackHandler(){}
    
    public SimpleCallbackHandler(String username, char[] password) 
    {
        this.username = username;
        this.password = password;
    }
    
    public SimpleCallbackHandler(String username, String password) 
    {
        this.username = username;
        this.password = password.toCharArray();
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException 
    {
        for (Callback callback : callbacks) 
            if (callback instanceof NameCallback) 
                ((NameCallback) callback).setName(username);
            else if (callback instanceof PasswordCallback)
                ((PasswordCallback) callback).setPassword((password));
    }
}

