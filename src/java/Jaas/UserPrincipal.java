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

public class UserPrincipal implements Principal
{
  private String userName;

  public UserPrincipal(String userName)
  {
    this.userName = userName;
  }

  public String getName() {
    return this.userName;
  }
}
