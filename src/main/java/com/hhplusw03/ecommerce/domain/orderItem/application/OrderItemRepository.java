package com.hhplusw03.ecommerce.domain.orderItem.application;

import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItemEntity;

public interface OrderItemRepository {
    public OrderItemEntity saveOrderItem(Long orderId, Long itemId, Long orderCount, Long price);
}
