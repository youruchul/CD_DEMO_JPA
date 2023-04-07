package com.papa.yogiyogi.domain.dto;

import com.papa.yogiyogi.domain.request.InsertAuctionCommentRequest;
import com.papa.yogiyogi.security.TokenInfo;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@ToString
public class AuctionCommentDTO {
    private Long id;
    private Long auctionId;
    private Long bidderId;
    private String bidderNickName;
    private String title;
    private String content;
    private ECondition pCondition;
    private Integer biddingPrice;
    private MultipartFile file;
    private String nameFile;

    public AuctionCommentDTO(TokenInfo tokenInfo, InsertAuctionCommentRequest request, Long id) {
        this.auctionId = id;
        this.title = request.getTitle();
        this.content = request.getContent();
        this.pCondition = request.getPCondition();
        this.biddingPrice = request.getBiddingPrice();
        this.file = request.getFile();
        this.nameFile = request.getNameFile();
        this.bidderId = tokenInfo.getId();
        this.bidderNickName = tokenInfo.getNickName();
    }
}
