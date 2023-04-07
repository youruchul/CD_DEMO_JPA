package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.ProductSell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ViewDetailProductSellResponse {
    private Long id ;
    private String title;
    private String content;
    private String url; //firebase picture
    private String sellerNickName;
    private ECategory category;
    private ECondition pCondition;
    private Integer price;
    private String buyerNickName;
    private Boolean isSold;

    public ViewDetailProductSellResponse(ProductSell productSell) {
        this.id = productSell.getId();
        this.title = productSell.getTitle();
        this.content = productSell.getContent();
        this.url = productSell.getUrl();
        this.sellerNickName = productSell.getSellerId().getNickName();
        this.category = productSell.getCategory();
        this.pCondition = productSell.getPCondition();
        this.price = productSell.getPrice();
        this.buyerNickName = productSell.getBuyerNickName();
        this.isSold = productSell.getIsSold();
    }

}
