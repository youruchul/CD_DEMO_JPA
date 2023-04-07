package com.papa.yogiyogi.domain.dto;

import com.papa.yogiyogi.domain.request.InsertProductSellRequest;
import com.papa.yogiyogi.domain.request.ProductSearchRequest;
import com.papa.yogiyogi.security.TokenInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSellDTO {
    private Long id;
    private String title;
    private String content;
    private Long sellerId;
    private String sellerNickName;
    private ECategory category;
    private ECondition pCondition;
    private Integer price;
    private MultipartFile file;
    private String nameFile;

    public ProductSellDTO(TokenInfo token, InsertProductSellRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.sellerId = token.getId();
        this.category = request.getCategory();
        this.pCondition = request.getPCondition();
        this.price = request.getPrice();
        this.file = request.getFile();
        this.nameFile = request.getNameFile();
    }
}
