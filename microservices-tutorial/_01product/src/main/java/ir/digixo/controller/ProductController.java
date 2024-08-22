package ir.digixo.controller;

import io.github.resilience4j.retry.annotation.Retry;
import ir.digixo.dto.ProductDTO;
import ir.digixo.entity.Product;
import ir.digixo.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProduct/{productName}")
    @Retry(name = "getProduct", fallbackMethod = "getProductFallBank")
    public ProductDTO getProduct(@PathVariable("productName") String productName) {
        return productService.getProduct(productName);
    }

    public ProductDTO getProductFallBank(@PathVariable("productName") String productName, Throwable throwable) {
        return new ProductDTO();
    }

    @PostMapping("/saveProduct")
    public void saveProduct(ProductDTO productDTO) {
        productService.saveProduct(productDTO);
    }
}
