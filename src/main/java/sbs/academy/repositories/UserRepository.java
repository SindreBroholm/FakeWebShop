package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByMail(String mail);
}
