package sbs.academy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import sbs.academy.data.Products;
import sbs.academy.data.User;
import sbs.academy.data.UserOrder;
import sbs.academy.repositories.UserOrderRepository;
import sbs.academy.repositories.UserRepository;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
class AcademyApplicationTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    void contextLoads() {
    }

    @Test
    public void testForDuplicateEmailInDatabase(){
        User user = new User(0, "sindre", "27", "s@s.no", "sindre", "sindre", "", "");
        List<User> userList = (List<User>)userRepository.findAll();
        for (User u : userList) {
            if (u.getMail().equals(user.getMail())) {
                if (u.getId() != user.getId()){
                    Assert.assertEquals("s@s.no", u.getMail());
                }
            }
        }
    }
}
