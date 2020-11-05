package sbs.academy;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sbs.academy.data.Products;
import sbs.academy.data.UserOrder;
import sbs.academy.repositories.UserOrderRepository;

import java.util.List;

@SpringBootTest
class AcademyApplicationTests {

    @Autowired
    private UserOrderRepository userOrderRepository;
    private Products products;
    private UserOrder userOrder;

    @Test
    void contextLoads() {
    }

    @Test
    public void testDeletingFromUserOrderRepositorie(){
        List<UserOrder> userOrdersList = userOrderRepository.findAllByUserId(2);
        int beforeDelete = userOrdersList.size();
        Assert.assertEquals(userOrdersList.size(), beforeDelete);
        userOrderRepository.deleteById(userOrdersList.get(1).getId());
        int afterDelete = userOrdersList.size();
        Assert.assertEquals(userOrdersList.size(), afterDelete);
        Assert.assertNotEquals(beforeDelete, afterDelete);
    }
}
