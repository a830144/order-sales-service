package com.mysap.sd.order.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysap.sd.order.entity.OrderHeader;
import com.mysap.sd.order.entity.OrderItem;
import com.mysap.sd.order.repository.OrderHeaderRepository;

@Service
public class OrderService {

    private final OrderHeaderRepository orderHeaderRepo;

    @Autowired
    public OrderService(OrderHeaderRepository orderHeaderRepo) {
        this.orderHeaderRepo = orderHeaderRepo;
    }

    public OrderHeader createOrder(OrderHeader orderHeader) {
        // Calculate totalAmount from items
        BigDecimal total = orderHeader.getItems().stream()
            .peek(item -> {
                item.setSubtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
                item.setOrder(orderHeader);
            })
            .map(OrderItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderHeader.setTotalAmount(total);
        orderHeader.setOrderDate(LocalDate.now());
        orderHeader.setStatus("new");
        return orderHeaderRepo.save(orderHeader);
    }

    public Optional<OrderHeader> getOrderById(Long id) {
        return orderHeaderRepo.findById(id);
    }

    public List<OrderHeader> getAllOrders() {
        return orderHeaderRepo.findAll();
    }
}
