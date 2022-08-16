package com.api.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity//@Entity가 붙은 클래스는 JPA가 관리하는 것으로, 엔티티라고 불림
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
