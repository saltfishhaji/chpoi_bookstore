/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.WebService;

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
@WebService(endpointInterface="User.WebService.IWebservice2", serviceName = "IWebservice2")
public class webservice2 implements IWebservice2{
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/external/register")
    @Override
    public String hello()
    {
        System.out.println("hello");
        return "hello";
    }
    
}
