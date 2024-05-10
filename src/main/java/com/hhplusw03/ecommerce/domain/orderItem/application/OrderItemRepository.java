package com.hhplusw03.ecommerce.domain.orderItem.application;

import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItem;

public interface OrderItemRepository {
    public OrderItem saveOrderItem(Long orderId, Long itemId, Long orderCount, Long price);
}
