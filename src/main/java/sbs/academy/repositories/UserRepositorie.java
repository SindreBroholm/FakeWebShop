package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.User;

public interface UserRepositorie extends CrudRepository<User, Integer> {
    User findByMail(String mail);
}
