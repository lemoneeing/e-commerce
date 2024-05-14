package com.hhplusw03.ecommerce.domain.orderItem.application;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class OrderItemServiceTest {
    private OrderItemRepository repo;
    private OrderItemService svc;

    @BeforeEach
    public void setup(){
        this.repo = mock(OrderItemRepository.class);
        this.svc = new OrderItemService(this.repo);
    }

    @Test
    public void 주문_상품_생성(){
        Long itemId = 1L;
        Long orderCount = 2L;
        Long price = 1000L;

        OrderItemDto createdOrderItemDto = this.svc.createOrderItem(itemId, orderCount, price);

        Assertions.assertThat(createdOrderItemDto).isInstanceOf(OrderItemDto.class);
        Assertions.assertThat(createdOrderItemDto.getOrderId()).isEqualTo(null);
        Assertions.assertThat(createdOrderItemDto.getItemId()).isEqualTo(itemId);
        Assertions.assertThat(createdOrderItemDto.getOrderCount()).isEqualTo(orderCount);
        Assertions.assertThat(createdOrderItemDto.getPrice()).isEqualTo(price);
    }

    @Test
    public void 주문_상품_저장(){
        Long orderId = 1L;
        Long itemId = 1L;
        Long orderCount = 2L;
        Long price = 1000L;

        OrderItemDto dto = new OrderItemDto(itemId, orderCount, price);
        dto.setOrderId(orderId);
        when(this.repo.saveOrderItem(orderId, itemId, orderCount, price)).thenReturn(dto.toEntity());

        OrderItemDto savedOrderItemDto = this.svc.saveOrderItem(dto);

        Assertions.assertThat(savedOrderItemDto).isInstanceOf(OrderItemDto.class);
        Assertions.assertThat(savedOrderItemDto.getOrderId()).isEqualTo(orderId);
        Assertions.assertThat(savedOrderItemDto.getItemId()).isEqualTo(itemId);
        Assertions.assertThat(savedOrderItemDto.getOrderCount()).isEqualTo(orderCount);
        Assertions.assertThat(savedOrderItemDto.getPrice()).isEqualTo(price);
    }
}