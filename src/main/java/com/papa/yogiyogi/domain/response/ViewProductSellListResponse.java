package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.entity.ProductSell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ViewProductSellListResponse {
    private Long id;
    private String title;
    private String url;
    private String sellerNickName;
    private ECategory category;
    private Integer price;
    private String buyerNickName;
    private Boolean isSold;

}
