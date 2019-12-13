package testgroup.crud_spring_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    public UserController(UserService userService, BarcodeService barcodeService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.barcodeService = barcodeService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(value = "/admin/userslist")
    public String allUsers(Model model) {
        List<User> users = userService.allUsers();
        List<Role> roles = roleService.getRoles();
        model.addAttribute("user", new User());
        model.addAttribute("usersList", users);
        model.addAttribute("roles", roles);
        return "users";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        Long barcodeId = user.getBarcode().getId();
        model.addAttribute("user", user);
        model.addAttribute("barcodeId", barcodeId);
        return "editPage";
    }

    @PostMapping(value = "/edit")
    public String editUser(User user, HttpServletRequest request) {
        User currentUser = userService.getById(user.getId());
        user.setBarcode(currentUser.getBarcode());

        if (user.getPassword().equals("")) {
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
    public String addPage() {
        return "editPage";
    }

    @GetMapping(value = "/login")
    public String loginPage(String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "Logout successfully");
        }
        return "login";
    }


    @PostMapping(value = "add")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        String role = request.getParameter("role");
        Set<Role> roles;
        if (role == null) {
            roles = Collections.singleton(roleService.getRoleById(1L));
        } else {
            roles = Collections.singleton(roleService.getRoleById(Long.valueOf(role)));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/userslist";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin/userslist";
    }

    @GetMapping(value = "/user/view/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        //        Тут есть косяк, если ввести не существующий id пользователя, покажется страничка с пустыми строками
        User user = userService.getById(id);
        model.addAttribute("user", user);
        try {
            Barcode barcode = barcodeService.getById(user.getBarcode().getId());
            model.addAttribute("barcode", barcode);
        } catch (NullPointerException e) {
            return "redirect:/";
        }
        return "showUser";
    }

}