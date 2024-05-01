package com.hhplusw03.ecommerce.domain.item.infrastructure;

import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import com.hhplusw03.ecommerce.domain.item.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemCoreRepository implements ItemRepository {
    private final ItemJpaRepository itemJpaRepository;

    @Autowired
    public ItemCoreRepository(ItemJpaRepository jpaRepo){
        this.itemJpaRepository = jpaRepo;
    }

    @Override
    public ItemEntity register(String name, Long price, String seller, Long stock) {
        return this.itemJpaRepository.save(new ItemEntity(name, price, seller, stock));
    }

    @Override
    public ItemEntity readItem(Long itemId) {
        return this.itemJpaRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item " + itemId + " 은 존재하지 않습니다."));
    }
}
