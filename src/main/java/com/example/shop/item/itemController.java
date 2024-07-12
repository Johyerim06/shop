package com.example.shop.item;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor

public class itemController {
    //상품과 관련된 API는 다 여기 보관하고 싶음
    //비슷한 API는 모아두는게 편함

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model){
        List<item> result = itemRepository.findAll(); //모든 행을 가져와서 ist자료형으로 가져옴
        //System.out.println(result.get(0).price);
        //System.out.println(result.get(0).title);

        /*var a = new ArrayList<>();
        a.add(30);
        a.add(40);
        System.out.println(a.get(0));
        System.out.println(a.get(1));*/

        //ArrayList<Object> a = new ArrayList<>();
        //List - ArrayList, LinkedList 의 상위 개념임
        //이러면 나중에 ArrayList, LinkedList로 변환 쉬움

        //itemRepository.findAll();
        model.addAttribute("items", result); //작명, 데이터
        var a = new item();
        System.out.println(a.toString());

        return "list.html"; //기본 경로가 static
    }

    @GetMapping("/write")
    String write(){
        return "write.html"; //기본 경로가 static
    }

    @PostMapping("/add")//url작명시 명사가 좋음
    String addPost(String title, Integer price){ //유저가 보낸 타입을 적은 형식으로 변환해 달라는 뜻임
        itemService.saveItem(title, price);
        return "redirect:/list"; //기본 경로가 static
    }

    @GetMapping("/detail/{id}")//url작명시 명사가 좋음
    String detail(@PathVariable Long id, Model model){ //유저가 보낸 타입을 적은 형식으로 변환해 달라는 뜻임


        Optional<item> result = itemRepository.findById(id);
        //Optional - 비어있을수도 있고 값이 없을수도 있습니다
        if(result.isPresent()){
            model.addAttribute("data",result.get());
            return "detail.html"; //기본 경로가 static
        } else {
            return "redirect:/list";
        }

    }
    @GetMapping("/edit/{id}")//url작명시 명사가 좋음
    String edit(Model model,@PathVariable Long id){

        Optional<item> result = itemRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @PostMapping("/edit")
    String editItem(String title,Integer price, Long id){

        itemService.editItem(title, price, id);
        return "redirect:/list";
    }


    @DeleteMapping("/item/{id}")
    ResponseEntity<String> deleteItem(@RequestParam Long id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

}
