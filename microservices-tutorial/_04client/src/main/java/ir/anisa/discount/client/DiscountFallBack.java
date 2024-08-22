package ir.anisa.discount.client;

import ir.anisa.discount.dto.Coupon;
import org.springframework.stereotype.Component;


@Component
public class DiscountFallBack implements DiscountClient {
    @Override
    public Coupon findByCouponCode(String code) {
        return null;
    }

    @Override
    public Coupon findByProductName(String productName) {
        return null;
    }
}