package com.example.simplecouponsystem.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CouponCreateProducer {

    private final KafkaTemplate<String, Long> kafkaTemplate;

    public CouponCreateProducer(final KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void create(final Long userId) {
        kafkaTemplate.send("coupon_create", userId);
    }
}
