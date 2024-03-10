package com.example.simplecouponsystem.service;

import com.example.simplecouponsystem.domain.Coupon;
import com.example.simplecouponsystem.repository.CouponCountRepository;
import com.example.simplecouponsystem.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;

    public ApplyService(final CouponRepository couponRepository,
                        final CouponCountRepository couponCountRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
    }

    public void apply(final Long userId) {
        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }


}
