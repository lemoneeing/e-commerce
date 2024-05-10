package com.hhplusw03.ecommerce.domain.orderItem.infrastructure;

import com.hhplusw03.ecommerce.domain.orderItem.application.OrderItemRepository;
import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemCoreRepository implements OrderItemRepository {
    private final OrderItemJpaRepository jpaRepository;

    public OrderItemCoreRepository(OrderItemJpaRepository jpaRepo){
        this.jpaRepository = jpaRepo;
    }

    @Override
    public OrderItem saveOrderItem(Long orderId, Long itemId, Long orderCount, Long price){
        return this.jpaRepository.save(new OrderItem(orderId, itemId, orderCount, price));
    }
}
