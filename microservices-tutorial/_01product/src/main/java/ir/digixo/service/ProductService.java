package ir.digixo.service;

import ir.anisa.notification.client.NotificationClient;
import ir.anisa.notification.dto.NotificationDTO;
import ir.digixo.dto.ProductDTO;
import ir.digixo.entity.Product;
import ir.digixo.repository.HibernateProductRepository;
import ir.digixo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HibernateProductRepository hibernateProductRepository;
    @Autowired
    private NotificationClient notificationClient;

    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setEntityIdentifier(productDTO.getName());
        notificationDTO.setSender("Product");
        notificationDTO.setMessage(productDTO.getName() + " saved successfully");
        notificationClient.saveNotification(notificationDTO);
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
