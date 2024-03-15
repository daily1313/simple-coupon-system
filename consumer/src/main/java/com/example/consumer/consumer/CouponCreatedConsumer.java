package com.example.consumer.consumer;

import com.example.consumer.domain.Coupon;
import com.example.consumer.repository.CouponRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CouponCreatedConsumer {

    private final CouponRepository couponRepository;

    public CouponCreatedConsumer(final CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @KafkaListener(topics = "coupon_create", groupId = "group_1")
    public void listener(final Long userId) {
        couponRepository.save(new Coupon(userId));
    }
}
