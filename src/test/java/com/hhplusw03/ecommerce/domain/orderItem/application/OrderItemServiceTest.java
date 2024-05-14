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

//    @Test
//    public void 단일_주문_상품_추가_서비스(){
//        Long orderId = 1L;
//        Long itemId = 1L;
//        Long orderCount = 1L;
//        Long price = 1000L;
//
//        OrderItemEntity orderIt = new OrderItemEntity(orderId, itemId, orderCount, price);
//        when(repo.saveOrderItem(orderId, itemId, orderCount, price)).thenReturn(orderIt);
//
//        OrderItemDto orderItDto = svc.createOrderItem(orderId, itemId, orderCount, price);
//
//        Assertions.assertThat(orderItDto).isInstanceOf(OrderItemDto.class);
//        Assertions.assertThat(orderItDto.getOrderId()).isEqualTo(orderId);
//        Assertions.assertThat(orderItDto.getItemId()).isEqualTo(itemId);
//        Assertions.assertThat(orderItDto.getOrderCount()).isEqualTo(orderCount);
//        Assertions.assertThat(orderItDto.getPrice()).isEqualTo(price);
//
//        verify(repo, times(1)).saveOrderItem(orderId, itemId, orderCount, price);
//    }
}