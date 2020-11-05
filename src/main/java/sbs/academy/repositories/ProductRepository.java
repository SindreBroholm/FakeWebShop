package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.Products;

public interface ProductRepository extends CrudRepository<Products, Integer> {
    Products findById(int productId);
}
