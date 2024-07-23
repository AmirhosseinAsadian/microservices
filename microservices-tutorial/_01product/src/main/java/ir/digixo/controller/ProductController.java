package ir.digixo.controller;

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
    public ProductDTO getProduct(@PathVariable("productName") String productName) {
        return productService.getProduct(productName);
    }

    @PostMapping("/saveProduct")
    public void saveProduct(ProductDTO productDTO) {
        productService.saveProduct(productDTO);
    }
}
