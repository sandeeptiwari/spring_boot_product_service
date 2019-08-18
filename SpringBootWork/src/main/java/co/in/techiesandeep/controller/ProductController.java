package co.in.techiesandeep.controller;

import co.in.techiesandeep.events.ProductEvent;
import co.in.techiesandeep.model.Product;
import co.in.techiesandeep.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ProductController extends AbstractController{
    Logger logger = LoggerFactory.getLogger("ProductController");

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/test")
    public String test() {
        return "hello";
    }

    @PostMapping("/product")
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        ProductEvent productCreatedEvent = new ProductEvent("One product is created", savedProduct);
        eventPublisher.publishEvent(productCreatedEvent);

        logger.info("Product Saved :" + savedProduct.toString());
        return product;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {

        Optional<Product> productRetreived = productService.get(id);
        Product product = productRetreived.get();
        ProductEvent productCreatedEvent = new ProductEvent("One product is retrieved", product);
        eventPublisher.publishEvent(productCreatedEvent);
        return product;
    }

    @GetMapping("/product/bypage")
    Page<Product> getProductsByPage(
            @RequestParam(value = "pagenumber", required = true, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pagesize", required = true, defaultValue = "20") Integer pageSize) {
        return productService.getProductByPage(pageNumber, pageSize);
    }

    @PutMapping("/product/{id}")
    @ResponseBody
    public Product updateProduct(@RequestBody Product product) {
        checkResourceFound(this.productService.get(product.getId()));
        Product productUpdated = productService.save(product);

        logger.info("Product Saved :" + productUpdated.toString());
        return product;
    }

    /*---Delete a Product by id---*/
    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        checkResourceFound(this.productService.get(id));
        productService.delete(id);
        return ResponseEntity.ok().body("Product has been deleted successfully.");
    }
}
