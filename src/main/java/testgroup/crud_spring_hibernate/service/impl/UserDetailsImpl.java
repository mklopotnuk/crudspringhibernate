package testgroup.crud_spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import testgroup.crud_spring_hibernate.dao.UserDAO;

import java.util.Collection;

@Service
public class UserDetailsImpl implements UserDetails {

    private UserDAO userDAO;

    public Long userId;

    @Autowired
    public UserDetailsImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public Long getId(){
        return userDAO.getById(1L).getId();
    }

}
