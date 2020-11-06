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
import sbs.academy.repositories.ProductRepository;
import sbs.academy.repositories.UserOrderRepository;
import sbs.academy.repositories.UserRepository;
import sbs.academy.validators.UserValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserOrderRepository userOrderRepository;
    private final PasswordEncoder passwordEncoder;

    public MainController(UserRepository userRepository, ProductRepository productRepository,
                          UserOrderRepository userOrderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.userOrderRepository = userOrderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String showHomePage(Principal principal, Model model) {
        if (isUserLogedIn(principal)) {
            User user = getLoggedInUser(principal);
            model.addAttribute("user", user);
            model.addAttribute("userOrders", userOrderRepository.findAllByUserId(user.getId()));
            model.addAttribute("cartTotalSum", getSumTotalForUsersCart(user.getId()));
            model.addAttribute("allUserProducts", userOrderRepository.getAllProductsToUser(user.getId()));
        }
        model.addAttribute("products", (List<Products>)productRepository.findAll());
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage(Principal principal) {
        if (isUserLogedIn(principal)) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model, Principal principal) {
        if (isUserLogedIn(principal)) {
            return "redirect:/";
        } else {
            model.addAttribute("user", new User());
            return "signup";
        }
    }

    @PostMapping("/signup")
    public String createNewUser(@ModelAttribute User user, BindingResult br) {
        validateUserClass(user, br);
        if (br.hasErrors()) {
            return "signup";
        } else {
            saveOrUpdateUser(user);
            return "login";
        }
    }

    @GetMapping("/profile")
    public String showProfilePage(Principal principal, Model model) {
        if (isUserLogedIn(principal)) {
            model.addAttribute("user", getLoggedInUser(principal));
            return "profile";
        }else {
            return "redirect:/";
        }
    }

    @GetMapping("/editprofile")
    public String showEditProfilePage(Principal principal, Model model) {
        if (isUserLogedIn(principal)) {
            model.addAttribute("user", getLoggedInUser(principal));
            return "editprofile";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editprofile")
    public String editProfile(Principal principal, @ModelAttribute User user, BindingResult br,  Model model) {
        if (isUserLogedIn(principal)){
            model.addAttribute("user", user);
            validateUserClass(user, br);
            if (br.hasErrors()) {
                return "editprofile";
            } else {
                saveOrUpdateUser(user);
                return "redirect:profile";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/addToCart/{ProductId}")
    public String addProductToCart(@ModelAttribute UserOrder userOrder, Principal principal, @PathVariable int ProductId){
        userOrderRepository.save(new UserOrder("In cart", getLoggedInUser(principal).getId(), ProductId));
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCart(Principal principal, Model model){
        if (isUserLogedIn(principal)) {
            model.addAttribute("usersCart", getUsersCart(getLoggedInUser(principal)));
            model.addAttribute("user", getLoggedInUser(principal));
            return "cart";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/removeFromCart/{userOrderId}")
    public String removeProductFromCart(@PathVariable int userOrderId, Principal principal){
        if (isUserLogedIn(principal)){
            userOrderRepository.deleteById(userOrderId);
        }
        return "redirect:/cart";
    }

    @GetMapping("/pay")
    public String showDonatePage(Principal principal, Model model){
        if (isUserLogedIn(principal)){
            model.addAttribute("user", getLoggedInUser(principal));
            model.addAttribute("cartTotalSum", getSumTotalForUsersCart(getLoggedInUser(principal).getId()));
        }
        return "Pay";
    }

    private void validateUserClass(@Valid User user, BindingResult br) {
        UserValidator userValidator = new UserValidator(userRepository);
        if (userValidator.supports(user.getClass())) {
            userValidator.validate(user, br);
        }
    }

    private boolean isUserLogedIn(Principal principal){
        return principal != null;
    }

    private User getLoggedInUser(Principal principal) {
        return userRepository.findByMail(principal.getName());
    }

    private int getSumTotalForUsersCart(int UserId) {
        List<Products> usersProducts = userOrderRepository.getAllProductsToUser(UserId);
        int total = 0;
        for (Products p : usersProducts) {
            total += Integer.parseInt(p.getPrice());
        }
        return total;
    }

    private List<DTO> getUsersCart(User user) {
        return addProductsToUsersCart(userOrderRepository.findAllByUserId(user.getId()));
    }

    private List<DTO> addProductsToUsersCart(List<UserOrder> allUserOrders) {
        List<DTO> dtoList = new ArrayList<>();
        for (UserOrder uo : allUserOrders) {
            dtoList.add(new DTO(uo, productRepository.findById(uo.getProduct_id())));
        }
        return dtoList;
    }

    private void saveOrUpdateUser(@Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfrimpassword(passwordEncoder.encode(user.getConfrimpassword()));
        userRepository.save(user);
    }
}
