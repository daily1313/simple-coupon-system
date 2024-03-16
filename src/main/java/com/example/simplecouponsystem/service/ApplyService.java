package com.example.simplecouponsystem.service;

import com.example.simplecouponsystem.domain.Coupon;
import com.example.simplecouponsystem.producer.CouponCreateProducer;
import com.example.simplecouponsystem.repository.AppliedUserRepository;
import com.example.simplecouponsystem.repository.CouponCountRepository;
import com.example.simplecouponsystem.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplyService {

    private final CouponRepository couponRepository;
    private final CouponCountRepository couponCountRepository;
    private final CouponCreateProducer couponCreateProducer;
    private final AppliedUserRepository appliedUserRepository;

    public ApplyService(final CouponRepository couponRepository,
                        final CouponCountRepository couponCountRepository,
                        final CouponCreateProducer couponCreateProducer,
                        final AppliedUserRepository appliedUserRepository) {
        this.couponRepository = couponRepository;
        this.couponCountRepository = couponCountRepository;
        this.couponCreateProducer = couponCreateProducer;
        this.appliedUserRepository = appliedUserRepository;
    }

    public void apply(final Long userId) {
        Long apply = appliedUserRepository.add(userId);

        if (apply != 1) {
            return;
        }

        long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

        couponCreateProducer.create(userId);
    }
}
