package ir.digixo.service;

import ir.digixo.dto.ProductDTO;
import ir.digixo.entity.Product;
import ir.digixo.repository.HibernateProductRepository;
import ir.digixo.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HibernateProductRepository hibernateProductRepository;
    public void saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        productRepository.save(product);
    }

    public ProductDTO getProduct(String productName) {
        Optional<Product> optionalProduct = hibernateProductRepository.findByName(productName);
        if (optionalProduct.isPresent()) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(optionalProduct.get(), productDTO);
            return productDTO;
        } else {
            throw new RuntimeException("product " + productName + " not found!");
        }
    }
}
