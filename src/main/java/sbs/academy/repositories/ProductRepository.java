package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findById(int productId);
}
