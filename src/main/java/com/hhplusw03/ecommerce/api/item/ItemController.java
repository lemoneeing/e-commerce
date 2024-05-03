package com.hhplusw03.ecommerce.api.item;

import com.hhplusw03.ecommerce.api.item.dto.request.ItemReqDto;
import com.hhplusw03.ecommerce.api.item.usecase.RegisterItemUseCase;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.item.usecase.ReadItemUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final RegisterItemUseCase itemRegUc;
    private final ReadItemUseCase itemReadUc;

    @Autowired
    public ItemController(RegisterItemUseCase regUc, ReadItemUseCase readUc ){
        this.itemRegUc = regUc;
        this.itemReadUc = readUc;
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseDto> registerItem(@RequestBody ItemReqDto itemReqDto){
        return ResponseEntity.status(HttpStatus.OK).body((ResponseDto) itemRegUc.execute(itemReqDto.getName(), itemReqDto.getPrice(), itemReqDto.getSeller(), itemReqDto.getStock()));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ResponseDto> readItemInfo(@PathVariable String itemId){
        // 단일 상품의 상세 페이지 조회
        return ResponseEntity.status(HttpStatus.OK).body((ResponseDto) itemReadUc.execute(itemId));
    }
}
