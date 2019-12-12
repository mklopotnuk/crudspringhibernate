package testgroup.crud_spring_hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import testgroup.crud_spring_hibernate.dao.UserDAO;
import testgroup.crud_spring_hibernate.model.Barcode;
import testgroup.crud_spring_hibernate.model.Role;
import testgroup.crud_spring_hibernate.model.User;
import testgroup.crud_spring_hibernate.service.UserService;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

//    private RolesDAO rolesDAO;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @Autowired
//    public UserServiceImpl(UserDAO userDAO, RolesDAO rolesDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userDAO = userDAO;
//        this.rolesDAO = rolesDAO;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }

    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Override
    public Long add(User user) {
        Barcode barcode = new Barcode();
        RestTemplate restTemplate = new RestTemplate();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Set<Role> roles = new HashSet<>();
//        roles.add(rolesDAO.getById(1L));
//        user.setRoles(roles);
        Long userId = userDAO.add(user);
        String formattedId = String.format("S%06d", userId);
        String url = "http://barcodes4.me/barcode/c128b/" + formattedId + ".png?resolution=2";
//        Possible exception
//        org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://barcodes4.me/barcode/c128b/S000009.png": barcodes4.me; nested exception is java.net.UnknownHostException: barcodes4.me
        byte[] imageBytes = restTemplate.getForObject(url, byte[].class);
        barcode.setBarcodeId(formattedId);
        String string = Base64.getEncoder().encodeToString(imageBytes);
        barcode.setBarcodeImage(string);
        user.setBarcode(barcode);
//        userDAO.edit(user);
        return userId;
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user.getId());
    }

    @Override
    public void edit(User user) {
//        При редактировании юзера в null сбрасывается barcode_id
            userDAO.edit(user);
        }

        @Override
        public User getById (Long id){
            return userDAO.getById(id);
        }

        @Override
        public User findByUserName (String name){
            return userDAO.findByUserName(name);
        }
    }
