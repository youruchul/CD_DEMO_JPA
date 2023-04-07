package com.papa.yogiyogi.domain.response;

import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.dto.ECondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ViewAuctionCommentListResponse {
    private Long id;
    private Long auctionId;
    private String bidderNickName;
    private String title;
    private ECondition pCondition;
    private Integer biddingPrice;
    private EAuctionStatus auctionStatus;
}
