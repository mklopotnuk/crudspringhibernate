package testgroup.crud_spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.crud_spring_hibernate.dao.UserDAO;
import testgroup.crud_spring_hibernate.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }else
        {
            return user;
        }

    }
}
