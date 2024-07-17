package ir.anisa.repository;

import ir.anisa.entity.Coupon;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Optional<Coupon> findByCode(String code);
    Optional<Coupon> findByProductName(String productName);
}
