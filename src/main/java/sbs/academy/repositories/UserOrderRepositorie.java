package sbs.academy.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import sbs.academy.data.Products;
import sbs.academy.data.UserOrder;

import java.util.List;

public interface UserOrderRepositorie extends CrudRepository<UserOrder, Integer> {

    @Query("select uo from UserOrder uo where uo.user_id = ?1")
    List<UserOrder> findAllByUserId(int userId);

    @Query("select p from UserOrder uo join Products p on uo.product_id = p.id " +
            "where p.id = uo.product_id and uo.user_id = ?1 ")
    List<Products> getAllProductsToUser(int userId);

    /*@Transactional
    @Modifying
    @Query("delete from UserOrder uo where uo.id = ?1 and uo.user_id = ?1 ")
    void deleteUserOrder(int userOrderId, int userId);*/
}
