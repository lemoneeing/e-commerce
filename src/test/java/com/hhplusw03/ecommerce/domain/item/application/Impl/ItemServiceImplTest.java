package com.hhplusw03.ecommerce.domain.item.application.Impl;

import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import com.hhplusw03.ecommerce.domain.item.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ItemServiceImplTest {
    ItemRepository repo;

    ItemServiceImpl svc;

    @BeforeEach
    public void setup(){
        repo = mock(ItemRepository.class);
        svc = new ItemServiceImpl(repo);
    }

    @Test
    public void 아이템_추가_서비스(){
        String name = "item1";
        Long price = 5000L;
        String seller = "seller1";
        Long stock = 10L;

        ItemEntity item = new ItemEntity("item1", 5000L, "seller1", 10L);
        when(repo.register(name, price, seller, stock)).thenReturn(item);

        ItemResult regItem = svc.registerItem(name, price, seller, stock);

        Assertions.assertThat(regItem).isInstanceOf(ItemDto.class);
        Assertions.assertThat(((ItemDto)regItem).getName()).isEqualTo(name);
        Assertions.assertThat(((ItemDto)regItem).getPrice()).isEqualTo(price);
        Assertions.assertThat(((ItemDto)regItem).getSeller()).isEqualTo(seller);
        Assertions.assertThat(((ItemDto)regItem).getStock()).isEqualTo(stock);

        verify(repo, times(1)).register(name, price, seller, stock);
    }

    @Test
    public void 아이템_재고_확인(){
        Long itemId1 = 1L;
        Long itemId2 = 2L;

        ItemEntity item1 = new ItemEntity("item1", 5000L, "seller1", 10L);
        ItemEntity item2 = new ItemEntity("item2", 5000L, "seller2", 0L);
        when(repo.readItem(itemId1)).thenReturn(item1);
        when(repo.readItem(itemId2)).thenReturn(item2);

        Boolean stockRes1 = svc.checkStock(itemId1);
        Boolean stockRes2 = svc.checkStock(itemId2);

        Assertions.assertThat(stockRes1).isInstanceOf(Boolean.class);
        Assertions.assertThat(stockRes1).isEqualTo(true);

        Assertions.assertThat(stockRes2).isInstanceOf(Boolean.class);
        Assertions.assertThat(stockRes2).isEqualTo(false);

        verify(repo, times(1)).readItem(itemId1);
        verify(repo, times(1)).readItem(itemId2);
    }

    @Test
    public void 존재하지_않는_아이템_재고_확인_실패(){
        Long itemId = 3L;
        EntityNotFoundException exp = new EntityNotFoundException("Item " + itemId + " 은 존재하지 않습니다.");
        when(repo.readItem(itemId)).thenThrow(exp);

        Assertions.assertThatThrownBy(()->svc.checkStock(itemId)).isInstanceOf(EntityNotFoundException.class);

        verify(repo, times(1)).readItem(itemId);
    }
}