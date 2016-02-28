package tcc_zanza.no_ip.biz.thirtyminutes.VO;

import java.io.Serializable;

/**
 * Created by VINICIUS on 12/28/2015.
 */
public class AccountData implements Serializable {

    private String userName;
    private String password;
    private String site;
    private Long id;

    public AccountData (){}

    public AccountData (String userName, String password, String site){
        this.userName = userName;
        this.password = password;
        this.site = site;
    }

    public String getUserName(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getSite(){
        return this.site;
    }
    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setSite(String site){
        this.site = site;
    }

}
