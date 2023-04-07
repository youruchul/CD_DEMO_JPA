package com.papa.yogiyogi.domain.entity;

import com.papa.yogiyogi.domain.dto.AuctionCommentDTO;
import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuctionComment {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    private AuctionBuy auctionId;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder_id")
    private User bidderId;
    private Integer biddingPrice;
    @Enumerated(EnumType.STRING)
    private ECondition pCondition;
    private String imgPath; //picture
    private String content;
    @Enumerated(EnumType.STRING)
    private EAuctionStatus auctionStatus;


    public AuctionComment(AuctionCommentDTO dto, String myImgPath, User user) {
        this.id = dto.getId();
        this.auctionId = new AuctionBuy(dto.getAuctionId());
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.bidderId = user;
        this.biddingPrice = dto.getBiddingPrice();
        this.pCondition = dto.getPCondition();
        this.imgPath = myImgPath;

    }
}
