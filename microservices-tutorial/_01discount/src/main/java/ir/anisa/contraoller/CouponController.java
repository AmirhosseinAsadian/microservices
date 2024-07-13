package ir.anisa.contraoller;

import ir.anisa.dto.CouponDTO;
import ir.anisa.entity.Coupon;
import ir.anisa.repository.CouponRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    @Autowired
    private CouponRepository couponRepository;


    @PostMapping
    public void createCoupon(@RequestBody Coupon coupon) {
        couponRepository.save(coupon);
    }


    @GetMapping("{code}")
    public CouponDTO findByCouponCode(@PathVariable("code") String code) {
        Optional<Coupon> byCode = couponRepository.findByCode(code);
        if (byCode.isPresent()) {
            CouponDTO couponDTO = new CouponDTO();
            Coupon coupon = byCode.get();
            BeanUtils.copyProperties(coupon, couponDTO);
            return couponDTO;
        } else {
            throw new RuntimeException("coupon " + code + " is not exist!");
        }
    }
}
