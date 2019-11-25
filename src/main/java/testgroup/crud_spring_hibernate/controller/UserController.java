package testgroup.crud_spring_hibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
//import testgroup.crud_spring_hibernate.service.SecurityService;
import testgroup.crud_spring_hibernate.service.RoleService;
import testgroup.crud_spring_hibernate.service.UserService;

import java.util.List;
//import testgroup.crud_spring_hibernate.validator.UserValidator;

@Controller
public class UserController {


//    private ModelAndView modelAndView;

    private UserService userService;
    private BarcodeService barcodeService;
    private RoleService roleService;


    //    private UserValidator userValidator;
    private String redirect = "redirect:/";

    public UserController(UserService userService, BarcodeService barcodeService, RoleService roleService) {
        this.userService = userService;
        this.barcodeService = barcodeService;
        this.roleService = roleService;
    }

//    @Autowired
//    public UserController(UserService userService, BarcodeService barcodeService, SecurityService securityService, UserValidator userValidator) {
//        this.userService = userService;
//        this.barcodeService = barcodeService;
//        this.securityService = securityService;
//        this.userValidator = userValidator;
//    }

//    @GetMapping(value = "/registration")
//    public String registration(Model model){
//        model.addAttribute("userForm",new User());
//        return "registration";
//    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/admin/userslist")
    public ModelAndView allUsers() {
        List<User> users = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return redirect;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping(value = "add")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult) {
        Long userId;
//        userValidator.validate(user,bindingResult);
//        if(bindingResult.hasErrors()){
//            return redirect;
//        }
        userId = userService.add(user);
//        securityService.autoLogin(user.getName(), user.getPassword());
//        userService.
        return "redirect:/view/" + userId;
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return redirect;
    }

    @GetMapping(value = "/view/{id}")
    public ModelAndView showUser(@PathVariable("id") Long id, ModelAndView modelAndView) {
        modelAndView.setViewName("showUser");
//        Тут есть косяк, если ввести не существующий id пользователя, покажется страничка с пустыми строками
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        try {
            Barcode barcode = barcodeService.getById(user.getBarcode().getId());
            modelAndView.addObject("barcode", barcode);
        } catch (NullPointerException e) {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

}
