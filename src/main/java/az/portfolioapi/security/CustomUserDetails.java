package az.portfolioapi.security;

import az.portfolioapi.entity.UserEntity;
import az.portfolioapi.entity.enums.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final boolean accountNonLocked;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.authorities = mapRolesToAuthorities(user.getRole());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(UserRole role) {
        if (role == null) {
            return Collections.emptyList();
        }
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }

    public UserRole getRole() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(UserRole::valueOf)
                .findFirst()
                .orElse(UserRole.MEMBER);
    }
}
