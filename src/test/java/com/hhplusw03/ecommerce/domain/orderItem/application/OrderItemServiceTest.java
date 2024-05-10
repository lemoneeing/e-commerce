package com.hhplusw03.ecommerce.domain.orderItem.application;

import com.hhplusw03.ecommerce.domain.orderItem.infrastructure.OrderItemCoreRepository;
import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({OrderItemService.class, OrderItemCoreRepository.class})
class OrderItemServiceTest {
    @MockBean
    private OrderItemCoreRepository repo;

    @Autowired
    private OrderItemService svc;

    @Test
    public void 단일_주문_상품_추가_서비스(){
        Long orderId = 1L;
        Long itemId = 1L;
        Long orderCount = 1L;
        Long price = 1000L;

        OrderItem orderIt = new OrderItem(orderId, itemId, orderCount, price);
        when(repo.saveOrderItem(orderId, itemId, orderCount, price)).thenReturn(orderIt);

        OrderItemDto orderItDto = svc.createOrderItem(orderId, itemId, orderCount, price);

        Assertions.assertThat(orderItDto).isInstanceOf(OrderItemDto.class);
        Assertions.assertThat(orderItDto.getOrderId()).isEqualTo(orderId);
        Assertions.assertThat(orderItDto.getItemId()).isEqualTo(itemId);
        Assertions.assertThat(orderItDto.getOrderCount()).isEqualTo(orderCount);
        Assertions.assertThat(orderItDto.getPrice()).isEqualTo(price);

        verify(repo, times(1)).saveOrderItem(orderId, itemId, orderCount, price);
    }
}