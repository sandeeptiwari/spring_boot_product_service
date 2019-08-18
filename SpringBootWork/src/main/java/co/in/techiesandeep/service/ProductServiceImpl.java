package co.in.techiesandeep.service;

import co.in.techiesandeep.model.Product;
import co.in.techiesandeep.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    final static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product save(Product product) {

        if (product.getCategory() == null) {
            logger.info("Product Category is null :");
        }else {
            logger.info("Product Category is not null :"+product.getCategory());
            logger.info("Product Category is not null ID :"+product.getCategory().getId());

        }

        if (product.getParentCategory() == null) {
            logger.info("Product Parent Category is null :");
        }else {
            logger.info("Product Parent Category is not null :"+product.getParentCategory());
            logger.info("Product Parent Category is not null Id :"+product.getParentCategory().getId());

        }
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> get(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> getProductByPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("productCode").descending());
        return productRepository.findAll(pageable);
    }

    @Override
    public void update(long id, Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

}