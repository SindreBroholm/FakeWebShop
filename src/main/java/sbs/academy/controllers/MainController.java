package sbs.academy.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sbs.academy.data.DTO;
import sbs.academy.data.Products;
import sbs.academy.data.User;
import sbs.academy.data.UserOrder;
import sbs.academy.repositories.ProductRepositorie;
import sbs.academy.repositories.UserOrderRepositorie;
import sbs.academy.repositories.UserRepositorie;
import sbs.academy.security.UserAccessService;
import sbs.academy.validators.UserValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private UserRepositorie userRepositorie;
    private ProductRepositorie productRepositorie;
    private UserOrderRepositorie userOrderRepositorie;
    private PasswordEncoder passwordEncoder;
    private UserAccessService userAccessService;
    private DTO dto;

    public MainController(UserRepositorie userRepositorie, ProductRepositorie productRepositorie,
                          UserOrderRepositorie userOrderRepositorie, PasswordEncoder passwordEncoder,
                          UserAccessService userAccessService) {
        this.userRepositorie = userRepositorie;
        this.productRepositorie = productRepositorie;
        this.userOrderRepositorie = userOrderRepositorie;
        this.passwordEncoder = passwordEncoder;
        this.userAccessService = userAccessService;;
    }

    @GetMapping("/")
    public String showHomePage(Principal principal, Model model) {
        model.addAttribute("principal", principal);
        if (principal != null) {
            User user = userAccessService.getLogedInUser(principal);
            model.addAttribute("user", user);
            List<UserOrder> allUserOrders = userOrderRepositorie.findAllByUserId(user.getId());
            model.addAttribute("userOrders", allUserOrders);
            List<Products> listOfAllProductsToUser = userOrderRepositorie.getAllProductsToUser(user.getId());
            int cartTotalSum = 0;
            for (Products p : listOfAllProductsToUser) {
                cartTotalSum += Integer.parseInt(p.getPrice());
            }
            model.addAttribute("cartTotalSum", cartTotalSum);
            model.addAttribute("allUserProducts", listOfAllProductsToUser);
        }
        List<Products> allProducts = (List<Products>) productRepositorie.findAll();
        model.addAttribute("products", allProducts);
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

    @PostMapping("/addToCart/{ProductId}")
    public String addProductToCart(@ModelAttribute UserOrder userOrder, Principal principal, @PathVariable int ProductId){
        int userId = userAccessService.getLogedInUser(principal).getId();
        userOrderRepositorie.save(new UserOrder("In cart", userId, ProductId));
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Principal principal, Model model){
        model.addAttribute("principal", principal);
        if (principal != null) {
            User user = userAccessService.getLogedInUser(principal);
            List<UserOrder> allUserOrders = userOrderRepositorie.findAllByUserId(user.getId());
            List<DTO> dtoList = new ArrayList<>();
            for (UserOrder uo : allUserOrders) {
                dtoList.add(new DTO(uo, productRepositorie.findById(uo.getProduct_id())));
            }
            model.addAttribute("DTO", dtoList);
            model.addAttribute("user", user);
        }
        return "cart";
    }

    @PostMapping("/removeFromCart/{userOrderId}")
    public String removeProductFromCart(@PathVariable int userOrderId, Principal principal){
        if (principal != null){
            userOrderRepositorie.deleteById(userOrderId);
        }
        return "redirect:/cart";
    }
}
