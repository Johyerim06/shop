package com.example.shop.item;

import com.example.shop.item.item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<item, Long> {

}
