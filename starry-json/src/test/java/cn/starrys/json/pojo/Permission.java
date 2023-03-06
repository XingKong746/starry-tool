package cn.starrys.json.pojo;

/**
 * creationTime: 2023/3/6 18:41 .
 *
 * @author XingKong
 */
public class Permission {
    private String permissionName;
    private String permissionDescription;
    private Other other;

    public Permission() {
    }

    public Permission(String permissionName, String permissionDescription, Other other) {
        this.permissionName = permissionName;
        this.permissionDescription = permissionDescription;
        this.other = other;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public Other getOther() {
        return other;
    }

    public void setOther(Other other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", other=" + other +
                '}';
    }
}
