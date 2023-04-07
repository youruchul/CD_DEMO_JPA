package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InsertAuctionBuyResponse {
    private Long id;
    private String title;
    private String content;
    private Long buyerId;
    private ECategory category;
    private ECondition minCondition;
    private Integer lowWishPrice;
    private Integer highWishPrice;
    private LocalDateTime timeout;
    private EAuctionStatus auctionStatus;

    public InsertAuctionBuyResponse(AuctionBuy auctionBuy) {
        this.id = auctionBuy.getId();
        this.title = auctionBuy.getTitle();
        this.content = auctionBuy.getContent();
        this.buyerId = auctionBuy.getBuyerId().getId();
        this.category = auctionBuy.getCategory();
        this.minCondition = auctionBuy.getMinCondition();
        this.lowWishPrice = auctionBuy.getLowWishPrice();
        this.highWishPrice = auctionBuy.getHighWishPrice();
        this.timeout = auctionBuy.getTimeout();
        this.auctionStatus = auctionBuy.getAuctionStatus();
    }
}
