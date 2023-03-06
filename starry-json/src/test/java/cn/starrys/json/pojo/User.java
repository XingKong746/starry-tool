package cn.starrys.json.pojo;

import java.util.ArrayList;

/**
 * creationTime: 2023/3/6 18:40 .
 *
 * @author XingKong
 */
public class User {
    private String username;
    private String password;
    private ArrayList<Role> roles;

    public User() {
    }

    public User(String username, String password, ArrayList<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
