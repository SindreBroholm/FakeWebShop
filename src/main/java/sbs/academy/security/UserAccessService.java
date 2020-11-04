package sbs.academy.security;

import org.springframework.stereotype.Service;
import sbs.academy.data.User;
import sbs.academy.repositories.ProductRepositorie;
import sbs.academy.repositories.UserOrderRepositorie;
import sbs.academy.repositories.UserRepositorie;

import java.security.Principal;

@Service
public class UserAccessService {

    private UserRepositorie userRepositorie;
    private ProductRepositorie productRepositorie;
    private UserOrderRepositorie userOrderRepositorie;

    public UserAccessService(UserRepositorie userRepositorie, ProductRepositorie productRepositorie, UserOrderRepositorie userOrderRepositorie) {
        this.userRepositorie = userRepositorie;
        this.productRepositorie = productRepositorie;
        this.userOrderRepositorie = userOrderRepositorie;
    }

    public User getLogedInUser(Principal principal) {
        String getUserMail = principal.getName();
        if (getUserMail != null) {
            User user = userRepositorie.findByMail(getUserMail);
            return user;
        }
        return null;
    }
}
