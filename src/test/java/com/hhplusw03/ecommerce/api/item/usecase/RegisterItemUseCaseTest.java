package com.hhplusw03.ecommerce.api.item.usecase;

import com.hhplusw03.ecommerce.domain.item.application.Impl.ItemServiceImpl;
import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({RegisterItemUseCase.class, ItemServiceImpl.class})
class RegisterItemUseCaseTest {
    @MockBean
    ItemServiceImpl svc;

    @Autowired
    RegisterItemUseCase uc;

    @Test
    public void 아이템_추가_유스케이스(){

        String name = "item1";
        Long price = 5000L;
        String seller = "seller1";
        Long stock = 10L;

        when(svc.registerItem(name, price, seller, stock)).thenReturn(new ItemDto("item1", 5000L, "seller1", 10L));

        ItemResult regItem = uc.execute(name, price, seller, stock);

        Assertions.assertThat(regItem).isInstanceOf(ItemDto.class);
        Assertions.assertThat(((ItemDto)regItem).getName()).isEqualTo(name);
        Assertions.assertThat(((ItemDto)regItem).getPrice()).isEqualTo(price);
        Assertions.assertThat(((ItemDto)regItem).getSeller()).isEqualTo(seller);
        Assertions.assertThat(((ItemDto)regItem).getStock()).isEqualTo(stock);

        verify(svc, times(1)).registerItem(name, price, seller, stock);
    }
}