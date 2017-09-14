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
import java.security.Principal;
import java.util.Objects;
public class SimplePrincipal implements Principal 
{
    private String role;
    public SimplePrincipal() {}
    public SimplePrincipal(String role) {this.role = role;}
    @Override
    public String getName() {return role;}
    public int hashCode() {return Objects.hashCode(getName());}
    public boolean equals(Object otherObject) 
    {
        if(this== otherObject) return true;
        if (otherObject == null) return false;
        if (getClass() != otherObject.getClass()) return false;
        SimplePrincipal other = (SimplePrincipal) otherObject;
        return Objects.equals(getName(), other.getName());
    }
}

