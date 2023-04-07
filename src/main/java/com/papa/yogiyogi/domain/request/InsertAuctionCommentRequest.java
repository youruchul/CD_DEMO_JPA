package com.papa.yogiyogi.domain.request;

import com.papa.yogiyogi.domain.dto.ECondition;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class InsertAuctionCommentRequest {

    private String title;
    private String content;
    private ECondition pCondition;
    private Integer biddingPrice;
    private MultipartFile file;
    private String nameFile;


}
