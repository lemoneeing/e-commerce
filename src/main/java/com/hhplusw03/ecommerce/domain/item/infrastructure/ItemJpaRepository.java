package com.hhplusw03.ecommerce.domain.item.infrastructure;

import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {

}
