package testgroup.crud_spring_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import testgroup.crud_spring_hibernate.model.Barcode;
import testgroup.crud_spring_hibernate.model.Role;
import testgroup.crud_spring_hibernate.model.User;
import testgroup.crud_spring_hibernate.service.BarcodeService;
import testgroup.crud_spring_hibernate.service.RoleService;
import testgroup.crud_spring_hibernate.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private BarcodeService barcodeService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private String defaultRedirect = "redirect:/";

    @Autowired
    public UserController(UserService userService, BarcodeService barcodeService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.barcodeService = barcodeService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/admin/userslist")
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        List<Role> roles = roleService.getRoles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("user", new User());
        modelAndView.addObject("usersList", users);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }

    @GetMapping(value = "/admin/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        Long barcodeId = user.getBarcode().getId();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        modelAndView.addObject("barcodeId", barcodeId);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String editUser(User user, HttpServletRequest request) {
        User currentUser = userService.getById(user.getId());
        user.setBarcode(currentUser.getBarcode());

        if (user.getPassword() == null) {
            user.setPassword(currentUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(Long.valueOf(request.getParameter("role"))));
        user.setRoles(roleSet);
        userService.edit(user);
        return "redirect:/admin/userslist";
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(String logout) {
        ModelAndView modelAndView = new ModelAndView();
        if (logout != null) {
            modelAndView.addObject("message", "Logout successfully");
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping(value = "add")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        String role = request.getParameter("role");
        Set<Role> roles = null;
        if (role == null) {
            roles = Collections.singleton(roleService.getRoleById(1L));
        } else {
            roles = Collections.singleton(roleService.getRoleById(Long.valueOf(role)));
        }
        user.setRoles(roles);
        Long userId;
        return "redirect:/admin/userslist";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin/userslist";
    }

    @GetMapping(value = "/user/view/{id}")
    public ModelAndView showUser(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("showUser");
//        Тут есть косяк, если ввести не существующий id пользователя, покажется страничка с пустыми строками
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        try {
            Barcode barcode = barcodeService.getById(user.getBarcode().getId());
            modelAndView.addObject("barcode", barcode);
        } catch (NullPointerException e) {
            modelAndView.setViewName(defaultRedirect);
        }
        return modelAndView;
    }

}