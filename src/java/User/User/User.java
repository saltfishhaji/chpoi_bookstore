/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.User;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer userid;
    private String username;
    private String password;
    private String email;
    private String mobile;
    public Integer getUserid() {return userid;}
    public void setUserid(Integer userid) {this.userid = userid;}
    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username = username;}
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password = password;}
    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}
    public String getMobile(){return this.mobile;}
    public void setMobile(String mobile){this.mobile = mobile;}
}
