package com.hhplusw03.ecommerce.api.item;

import com.hhplusw03.ecommerce.api.item.dto.response.ItemResDto;
import com.hhplusw03.ecommerce.api.item.usecase.ReadItemUseCase;
import com.hhplusw03.ecommerce.api.item.usecase.RegisterItemUseCase;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ItemController.class, ReadItemUseCase.class})
class ItemControllerTest {
    @MockBean
    RegisterItemUseCase regUc;
    @MockBean
    ReadItemUseCase readUc;
    @Autowired
    ItemController controller;

    @Test
    public void 아이템_조회(){
        String itemId = "1";
        String name = "item1";
        Long price = 1000L;
        String seller = "seller1";
        Long stock = 10L;

        ResponseDto itemResDto = new ItemResDto(name, price, seller, stock);
        when(readUc.execute(itemId)).thenReturn(itemResDto);

        ResponseEntity<ResponseDto> resp = controller.readItemInfo(itemId);

        Assertions.assertThat(resp).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(((ItemResDto)resp.getBody()).getName()).isEqualTo(name);
        Assertions.assertThat(((ItemResDto)resp.getBody()).getPrice()).isEqualTo(price);
        Assertions.assertThat(((ItemResDto)resp.getBody()).getSeller()).isEqualTo(seller);
        Assertions.assertThat(((ItemResDto)resp.getBody()).getStock()).isEqualTo(stock);

        verify(readUc, times(1)).execute(itemId);
    }

}