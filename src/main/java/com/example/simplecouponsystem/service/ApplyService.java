package com.example.simplecouponsystem.service;

import com.example.simplecouponsystem.domain.Coupon;
import com.example.simplecouponsystem.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;

    public ApplyService(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public void apply(final Long userId) {
        long count = couponRepository.count();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
