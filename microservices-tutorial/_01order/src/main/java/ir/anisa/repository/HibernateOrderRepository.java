package ir.anisa.repository;

import ir.anisa.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateOrderRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void saveOrder(Order order) {
        entityManager.persist(order);
    }
}
