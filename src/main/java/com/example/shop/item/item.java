package com.example.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//컬럼에는 이거 붙이라고 강요함
    public Long id;

    @Column
    //nullable = false, unique = true
    //columnDefinition = "TEXT" 컬럼타입 강제로 지정 가능
    private String title;
    private Integer price; //컬럼용 변수에는 int대신 Integer사용 강요

}
