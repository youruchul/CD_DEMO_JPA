package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertAuctionCommentResponse {
    private Long id;
    private Long auctionId;
    private String title;
    private String content;
    private Long bidderId;
    private String bidderNickName;
    private ECondition pCondition;
    private Integer biddingPrice;
    private String imgPath;
    public InsertAuctionCommentResponse(AuctionComment auctionComment) {
        this.id = auctionComment.getId();
        this.auctionId = auctionComment.getAuctionId().getId();
        this.title = auctionComment.getTitle();
        this.content = auctionComment.getContent();
        this.bidderId = auctionComment.getBidderId().getId();
        this.bidderNickName = auctionComment.getBidderId().getNickName();
        this.pCondition = auctionComment.getPCondition();
        this.biddingPrice = auctionComment.getBiddingPrice();
        this.imgPath = auctionComment.getImgPath();
    }
}
