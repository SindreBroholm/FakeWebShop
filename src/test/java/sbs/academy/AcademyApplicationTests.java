package sbs.academy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sbs.academy.data.Products;
import sbs.academy.data.UserOrder;
import sbs.academy.repositories.UserOrderRepositorie;

import java.util.List;

@SpringBootTest
class AcademyApplicationTests {

    @Autowired
    private UserOrderRepositorie userOrderRepositorie;
    private Products products;
    private UserOrder userOrder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDeletingFromUserOrderRepositorie(){
        List<UserOrder> userOrdersList = userOrderRepositorie.findAllByUserId(2);
        int beforeDelete = userOrdersList.size();
        Assert.assertEquals(userOrdersList.size(), beforeDelete);
        userOrderRepositorie.deleteById(userOrdersList.get(1).getId());
        int afterDelete = userOrdersList.size();
        Assert.assertEquals(userOrdersList.size(), afterDelete);
        Assert.assertNotEquals(beforeDelete, afterDelete);
    }
}
