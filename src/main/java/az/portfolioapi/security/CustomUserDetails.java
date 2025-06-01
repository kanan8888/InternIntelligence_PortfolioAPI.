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
import java.util.stream.Collectors;

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
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;

        Set<UserRole> roles = user.getRole() == null
                ? Set.of()
                : Set.of(user.getRole());

        this.authorities = mapRolesToAuthorities(roles);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<UserRole> roles) {
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet());
    }

    public UserRole getRole() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(auth -> auth.replace("ROLE_", ""))
                .map(UserRole::valueOf)
                .findFirst()
                .orElse(UserRole.MEMBER);
    }

    //gələcəkdə Userlərin birdən çox rolu ola bilər deyə.... əvvəlki methodun adını getFirstRole() olaraq dəyişmək olar...
    public Set<UserRole> getRoles() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(auth -> auth.replace("ROLE_", ""))
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }
}





/*
//hər ehtimala işləyən versiyasını saxlayım))



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
        this.username = user.getUsername();
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
        return Set.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    public UserRole getRole() {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(UserRole::valueOf)
                .findFirst()
                .orElse(UserRole.MEMBER);
    }
}


 */