package sbs.academy.repositories;

import org.springframework.data.repository.CrudRepository;
import sbs.academy.data.Products;

public interface ProductRepositorie extends CrudRepository<Products, Integer> {
    Products findById(int productId);
}
