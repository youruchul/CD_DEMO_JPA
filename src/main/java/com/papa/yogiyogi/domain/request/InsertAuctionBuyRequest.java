package com.papa.yogiyogi.domain.request;

import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InsertAuctionBuyRequest {
    private String title;
    private String content;
    private ECategory category;
    private ECondition minCondition;
    private Integer lowWishPrice;
    private Integer highWishPrice;
    private Long inputTime;
    private EAuctionStatus eAuctionStatus = EAuctionStatus.PROCEEDING;
}
