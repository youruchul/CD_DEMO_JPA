package com.papa.yogiyogi.domain.entity;

import com.papa.yogiyogi.domain.dto.AuctionBuyDTO;
import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.request.InsertAuctionBuyRequest;
import com.papa.yogiyogi.domain.request.InsertAuctionCommentRequest;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuctionBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyerId;
    @Enumerated(EnumType.STRING)
    private ECategory category;
    @Enumerated(EnumType.STRING)
    private ECondition minCondition;
    private Integer lowWishPrice;
    private Integer highWishPrice;
    private LocalDateTime timeout;
    @Enumerated(EnumType.STRING)
    private EAuctionStatus auctionStatus;
    private Long sellerId = null;

    @OneToMany(mappedBy = "auctionId", fetch = FetchType.LAZY)
    private List<AuctionComment> auctionComments = new ArrayList<>();

    public AuctionBuy(Long id) {
        this.id = id;
    }
    public AuctionBuy(AuctionBuyDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.buyerId = new User(dto.getBuyerId());
        this.category = dto.getCategory();
        this.minCondition = dto.getMinCondition();
        this.lowWishPrice = dto.getLowWishPrice();
        this.highWishPrice = dto.getHighWishPrice();
        this.timeout = Instant.ofEpochMilli(dto.getInputTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        this.auctionStatus = dto.getAuctionStatus();
    }

}