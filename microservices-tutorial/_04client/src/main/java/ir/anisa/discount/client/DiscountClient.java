package ir.anisa.discount.client;

import ir.anisa.discount.dto.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DISCOUNT")
public interface DiscountClient {

    @GetMapping("/api/v1/coupon/getCoupon/{code}")
    Coupon findByCouponCode(@PathVariable("code") String code);

    @GetMapping("/api/v1/coupon/getCouponByProduct/{productName}")
    Coupon findByProductName(@PathVariable("productName") String productName);

}