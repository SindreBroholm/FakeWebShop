package sbs.academy.security.authority;

public enum UserPermission {
    SHOPPER_READ("shopper:read"),
    SHOPPER_WRITE("shopper:write"),
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
