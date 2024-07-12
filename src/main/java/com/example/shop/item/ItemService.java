package com.example.shop.item;

import com.example.shop.item.ItemRepository;
import com.example.shop.item.item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service //원래 Service클래스에는 기본으로 붙여야 함
@RequiredArgsConstructor

public class ItemService {
    //이거저거 검사하거나 DB입출력하거나-> 비즈니스 로직
    //비즈니스 로직 담는 클래스는 Service라고 부름

    private final ItemRepository itemRepository;
    public void saveItem(String title, Integer price){
        item Item = new item();
        Item.setTitle(title);
        Item.setPrice(price);
        itemRepository.save(Item);
        /*DB입출력하려면 뭐부터 해야함?
        1. repository interface만들고
        2. 그 변수를 등록하고
        3. 사용
         */
    }

    String editItem(String title,Integer price, Long id){

        if (title.length() > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title length exceeds 100 characters");
        }

        if (price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Price cannot be negative");
        }

        item Item = new item();
        Item.setId(id);
        Item.setTitle(title); // 아이디가 1인 행이 있으면 이 내용으로 새로 만들고, 없으면 덮어쓰기
        Item.setPrice(price);
        itemRepository.save(Item);
        return "redirect:/list";
    }
}
