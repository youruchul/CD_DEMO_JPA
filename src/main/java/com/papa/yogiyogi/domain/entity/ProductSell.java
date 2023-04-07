package com.papa.yogiyogi.domain.entity;

import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.dto.ProductSellDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSell {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title;
    private String url; //firebase picture
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User sellerId;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    @Enumerated(EnumType.STRING)
    private ECondition pCondition;
    private Integer price;
    private String  buyerNickName = "";
    private Boolean isSold = false;


    public ProductSell(ProductSellDTO dto , String myImgPath) {
        this.title = dto.getTitle();
        this.url = myImgPath;
        this.content = dto.getContent();
        this.sellerId = new User(dto.getSellerId());
        this.category = dto.getCategory();
        this.pCondition = dto.getPCondition();
        this.price = dto.getPrice();
    }
}
