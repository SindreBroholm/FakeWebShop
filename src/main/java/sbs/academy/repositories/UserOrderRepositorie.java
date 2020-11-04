package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.UserOrder;

public interface UserOrderRepositorie extends CrudRepository<UserOrder, Integer> {
}
