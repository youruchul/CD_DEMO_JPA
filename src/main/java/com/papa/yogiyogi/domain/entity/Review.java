package com.papa.yogiyogi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.papa.yogiyogi.domain.dto.EReviewType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_sell_id")
    private ProductSell productSellId;
    private String content;
    private Integer rating;
    private Long writerId;
    private Long receiverId;
    @Enumerated(EnumType.STRING)
    private EReviewType reviewType;
}
