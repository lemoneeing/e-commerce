package com.hhplusw03.ecommerce.domain.orderItem.infrastructure;

import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
