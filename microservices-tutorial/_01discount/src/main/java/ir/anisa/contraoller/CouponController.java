package ir.anisa.contraoller;

import ir.anisa.dto.CouponDTO;
import ir.anisa.entity.Coupon;
import ir.anisa.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) {
        couponService.createCoupon(coupon);
    }


    @GetMapping("/getCoupon/{code}")
    public CouponDTO findByCouponCode(@PathVariable("code") String code) {
        return couponService.findByCouponCode(code);
    }

    @GetMapping("/getCouponByProduct/{productName}")
    public CouponDTO findByProductName(@PathVariable("productName") String productName) {
        return couponService.findByProductName(productName);
    }
}
