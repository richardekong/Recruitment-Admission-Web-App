package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import java.io.Serializable;
public class ManageUser implements Serializable {
    private Long uid;

    private String username;

    private String password;

    private String userRole;


    public ManageUser() {

    }

    public ManageUser(Long uid, String username, String password, String userRole) {
        this.uid = uid;
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