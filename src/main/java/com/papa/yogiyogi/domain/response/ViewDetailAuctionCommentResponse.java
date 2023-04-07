package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import com.papa.yogiyogi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ViewDetailAuctionCommentResponse {


    private Long auctionId;
    private String bidderNickName;
    private String title;
    private String content;
    private ECondition pCondition;
    private Integer biddingPrice;
    private String url;

    public ViewDetailAuctionCommentResponse(AuctionComment auctionComment) {
        this.auctionId = auctionComment.getAuctionId().getId();
        this.bidderNickName = auctionComment.getBidderId().getNickName();
        this.title = auctionComment.getTitle();
        this.content = auctionComment.getContent();
        this.pCondition = auctionComment.getPCondition();
        this.biddingPrice = auctionComment.getBiddingPrice();
        this.url = auctionComment.getImgPath();
    }
}
