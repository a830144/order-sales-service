package com.mysap.sd.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysap.sd.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
