package com.hhplusw03.ecommerce.domain.orderItem.application;

import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepo;

    public OrderItemService(OrderItemRepository repo){
        this.orderItemRepo = repo;
    }

    public OrderItemDto createOrderItem(Long itemId, Long orderCount, Long price){
        return new OrderItemDto(itemId, orderCount, price);
    }

    public OrderItemDto saveOrderItem(Long orderId, Long itemId, Long orderCount, Long price){
        return new OrderItemDto(this.orderItemRepo.saveOrderItem(orderId, itemId, orderCount, price));
    }

    public OrderItemDto saveOrderItem(OrderItemDto dto){
        return new OrderItemDto(this.orderItemRepo.saveOrderItem(dto.getOrderId(), dto.getItemId(), dto.getOrderCount(), dto.getPrice()));
    }
}
