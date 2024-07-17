package ir.anisa.service;

import ir.anisa.dto.CouponDTO;
import ir.anisa.entity.Coupon;
import ir.anisa.repository.CouponRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class CouponService {

    private CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void createCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }


    public CouponDTO findByCouponCode(String code) {
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

    public CouponDTO findByProductName(String code) {
        Optional<Coupon> byCode = couponRepository.findByProductName(code);
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
