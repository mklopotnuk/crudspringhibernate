//package testgroup.crud_spring_hibernate.validator;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//import testgroup.crud_spring_hibernate.model.User;
//import testgroup.crud_spring_hibernate.service.UserService;
//
//@Component
//public class UserValidator implements Validator {
//
//    private UserService userService;
//
//    @Autowired
//    public UserValidator(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return User.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        User user = (User) o;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name", "Required");
//        if(user.getName().length()<6 || user.getName().length()>32){
//            errors.rejectValue("name", "Size.loginForm.username");
//        }
//
//        if(userService.findByUserName(user.getName())!=null){
//            errors.rejectValue("name","Duplicate.userForm.username");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "Required");
//        if (user.getPassword().length()<3 || user.getPassword().length()>32){
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//
//        if(!user.getConfirmPassword().equals(user.getPassword())){
//            errors.rejectValue("confirmPassword", "Different.userForm.password");
//        }
//    }
//
//}
