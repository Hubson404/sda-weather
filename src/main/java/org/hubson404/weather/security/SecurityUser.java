package org.hubson404.weather.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Data
public class SecurityUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;

//    private List<GrantedAuthority> authorities; // nie mozemy zapisac listy do bazy danych bez tworzenia relacji dlatego tworzymy Stringa (serializacja danych)
    private String authorities;

    public List<GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(";"))
                .map(s -> (GrantedAuthority) () -> s) // zapis dla interface'u funkcyjnego
                .collect(Collectors.toList());
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(";"));
    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(()->"ROLE_USER");
//        authorities.add(()->"ROLE_USER");
//
//        return null;
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
