package sbs.academy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.Product;
import sbs.academy.data.UserOrder;

import java.util.List;

public interface UserOrderRepository extends CrudRepository<UserOrder, Integer> {

    @Query("select uo from UserOrder uo where uo.user_id = ?1")
    List<UserOrder> findAllByUserId(int userId);

    @Query("select p from UserOrder uo join Product p on uo.product_id = p.id " +
            "where p.id = uo.product_id and uo.user_id = ?1 ")
    List<Product> getAllProductsToUser(int userId);

}
