package co.in.techiesandeep.service;

import co.in.techiesandeep.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product save(Product product);
    Optional<Product> get(long id);
    Page<Product> getProductByPage(Integer pageNum, Integer pageSize);
    void update(long id, Product product);
    void delete(long id);
}
