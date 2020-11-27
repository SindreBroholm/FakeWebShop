package sbs.academy.security.authority;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static sbs.academy.security.authority.UserPermission.*;


public enum UserRole {

    SHOPPER(Sets.newHashSet(SHOPPER_READ, SHOPPER_WRITE)),
    ADMIN(Sets.newHashSet(ADMIN_READ, ADMIN_WRITE, SHOPPER_READ, SHOPPER_WRITE));


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permission = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }
}
