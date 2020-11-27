package sbs.academy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("select u from User u where u.mail = ?1 ")
    User findByMail(String mail);
}
