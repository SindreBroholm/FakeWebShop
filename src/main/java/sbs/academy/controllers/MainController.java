package sbs.academy.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sbs.academy.data.User;
import sbs.academy.repositories.ProductRepositorie;
import sbs.academy.repositories.UserOrderRepositorie;
import sbs.academy.repositories.UserRepositorie;
import sbs.academy.security.UserAccessService;
import sbs.academy.validators.UserValidator;

import java.security.Principal;

@Controller
public class MainController {

    private UserRepositorie userRepositorie;
    private ProductRepositorie productRepositorie;
    private UserOrderRepositorie userOrderRepositorie;
    private PasswordEncoder passwordEncoder;
    private UserAccessService userAccessService;

    public MainController(UserRepositorie userRepositorie, ProductRepositorie productRepositorie,
                          UserOrderRepositorie userOrderRepositorie, PasswordEncoder passwordEncoder,
                          UserAccessService userAccessService) {
        this.userRepositorie = userRepositorie;
        this.productRepositorie = productRepositorie;
        this.userOrderRepositorie = userOrderRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.userAccessService = userAccessService;
    }

    @GetMapping("/")
    public String showHomePage(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        if (principal != null) {
            User user = userAccessService.getLogedInUser(principal);
            model.addAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage(Principal principal) {

        if (principal != null) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/signup")
    public String createNewUser(@ModelAttribute User user, BindingResult br) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.supports(user.getClass())) {
            userValidator.validate(user, br);
        }
        if (br.hasErrors()) {
            return "signup";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepositorie.save(user);
            return "login";
        }
    }

    @GetMapping("/profile")
    public String showProfilePage(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        if (principal != null) {
            User user = userAccessService.getLogedInUser(principal);
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @GetMapping("/editprofile")
    public String showEditProfilePage(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        if (principal != null) {
            User user = userAccessService.getLogedInUser(principal);
            model.addAttribute("user", user);
        }
        return "editprofile";
    }


    @PostMapping("/editprofile")
    public String editProfile(Principal principal, @ModelAttribute User user, BindingResult br, Model model) {
        model.addAttribute("principal", principal);
        UserValidator userValidator = new UserValidator();
        if (userValidator.supports(user.getClass())) {
            userValidator.validate(user, br);
        }
        if (br.hasErrors()) {
            return "editprofile";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepositorie.save(user);
            return "redirect:profile";
        }
    }

}
