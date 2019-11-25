//package testgroup.crud_spring_hibernate.service.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import testgroup.crud_spring_hibernate.service.SecurityService;
//
//
//@Service
//public class SecurityServiceImpl implements SecurityService {
//
//    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
//
//    private AuthenticationManager authenticationManager;
//    private UserDetailsService userDetailsService;
//
////    @Autowired
////    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
////        this.authenticationManager = authenticationManager;
////        this.userDetailsService = userDetailsService;
////    }
//
//    @Override
//    public String findLoggedInUsername() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        if(userDetails instanceof UserDetails){
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
//    }
//
//    @Override
//    public void autoLogin(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
//        authenticationManager.authenticate(authenticationToken);
//        if(authenticationToken.isAuthenticated()){
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            logger.debug(String.format("Successfully %s auto logged in.", username));
//        }
//
//    }
//}
