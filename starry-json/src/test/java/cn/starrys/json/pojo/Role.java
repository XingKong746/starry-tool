package cn.starrys.json.pojo;

import java.util.ArrayList;

/**
 * creationTime: 2023/3/6 18:41 .
 *
 * @author XingKong
 */
public class Role {
    private String roleName;
    private String roleDescription;
    private ArrayList<Permission> permissions;

    public Role() {
    }

    public Role(String roleName, String roleDescription, ArrayList<Permission> permissions) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.permissions = permissions;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
