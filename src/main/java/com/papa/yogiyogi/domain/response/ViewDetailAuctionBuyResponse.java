package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ViewDetailAuctionBuyResponse {
    private Long id;
    private String title;
    private String content;
    private String buyerNickName;
    private ECategory category;
    private ECondition minCondition;
    private Integer lowWishPrice;
    private Integer highWishPrice;
    private LocalDateTime timeout;
    private EAuctionStatus auctionStatus;
    private Integer commentCount = 0;


    public ViewDetailAuctionBuyResponse(AuctionBuy auctionBuy, Integer commentCount) {
        this.id = auctionBuy.getId();
        this.title = auctionBuy.getTitle();
        this.content = auctionBuy.getContent();
        this.buyerNickName = auctionBuy.getBuyerId().getNickName();
        this.category = auctionBuy.getCategory();
        this.minCondition = auctionBuy.getMinCondition();
        this.lowWishPrice = auctionBuy.getLowWishPrice();
        this.highWishPrice = auctionBuy.getHighWishPrice();
        this.timeout = auctionBuy.getTimeout();
        this.auctionStatus = auctionBuy.getAuctionStatus();
        this.commentCount = commentCount;
    }
}
