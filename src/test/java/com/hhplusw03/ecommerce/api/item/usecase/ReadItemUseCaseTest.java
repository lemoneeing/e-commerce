package com.hhplusw03.ecommerce.api.item.usecase;

import com.hhplusw03.ecommerce.api.item.dto.response.ItemResDto;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.domain.item.application.Impl.ItemServiceImpl;
import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ReadItemUseCase.class, ItemServiceImpl.class})
class ReadItemUseCaseTest {
    @MockBean
    private ItemServiceImpl svc;

    @Autowired
    private ReadItemUseCase uc;

    @Test
    public void 아이템_조회(){
        Long itemId = 1L; // ItemEntity 가 실제 DB에 삽입될 때가 되어야 PK 값이 생성되므로 지금은 임시로 1L 을 item 의 id 로 설정.
        String name = "item1";
        Long price = 5000L;
        String seller = "seller1";
        Long stock = 10L;
        ItemEntity item = new ItemEntity(name, price, seller, stock);

        when(svc.readItem(itemId)).thenReturn(new ItemDto(item));

        ResponseDto readItem = uc.execute(String.valueOf(itemId));

        Assertions.assertThat(readItem).isInstanceOf(ItemResDto.class);
        Assertions.assertThat(((ItemResDto)readItem).getName()).isEqualTo(name);
        Assertions.assertThat(((ItemResDto)readItem).getPrice()).isEqualTo(price);
        Assertions.assertThat(((ItemResDto)readItem).getSeller()).isEqualTo(seller);
        Assertions.assertThat(((ItemResDto)readItem).getStock()).isEqualTo(stock);

        verify(svc, times(1)).readItem(itemId);
    }
}