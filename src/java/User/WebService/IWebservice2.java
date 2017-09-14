/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.WebService;

import javax.jws.WebMethod;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.jws.WebService;
/**
 *
 * @author Administrator
 */
@WebService
public interface IWebservice2 {
     @WebMethod(operationName="hello")
    public String hello();
    
}
