package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "managed_user")
public class ManagedUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uid;
    private String username;

    private String password;
    private String userRole;


    public ManagedUser() {

    }

    public ManagedUser(String username, String password, String userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Long getUid() {
        return uid;
    }

    public void setUserId(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}