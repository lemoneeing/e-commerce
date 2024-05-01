package com.hhplusw03.ecommerce.domain.item.application.Impl;

import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import com.hhplusw03.ecommerce.domain.item.infrastructure.ItemCoreRepository;
import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ItemServiceImpl.class, ItemCoreRepository.class})
class ItemServiceImplTest {
    @MockBean
    ItemCoreRepository repo;

    @Autowired
    ItemServiceImpl svc;

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
}