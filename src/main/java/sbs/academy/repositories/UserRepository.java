package sbs.academy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByMail(String mail);

    @Query("select u.mail from User u")
    List<String> findAllByMail();
}
