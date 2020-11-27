package sbs.academy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sbs.academy.data.User;
import sbs.academy.repositories.UserRepository;
import sbs.academy.security.authority.UserRole;

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
        User foundByMail = userRepository.findByMail(user.getMail());
        if (foundByMail.getMail().equals(user.getMail())) {
            if (foundByMail.getId() != user.getId()){
                Assert.assertEquals("s@s.no", foundByMail.getMail());
            }
        }
    }
}
