package com.mysap.sd.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysap.sd.order.entity.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}

