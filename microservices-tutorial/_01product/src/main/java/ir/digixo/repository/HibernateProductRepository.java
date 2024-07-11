package ir.digixo.repository;

import ir.digixo.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class HibernateProductRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    private void saveProduct(Product product) {
        entityManager.persist(product);
    }

    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Optional<Product> findByName(String productName) {
        List<Product> products = entityManager.createQuery("select p from Product p where p.name = :name").setParameter("name", productName).getResultList();
        if (Objects.nonNull(products) && !products.isEmpty()) {
            return products.stream().findFirst();
        } else {
            throw new RuntimeException("product " + productName + " not found!");
        }
    }

}
