package com.papa.yogiyogi.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String userPw;
    private String nickName;
    private String birth; //yymmdd
    private String phoneNumber;
    private String email;

    public User(String userId, String userPw, String nickName, String birth, String phoneNumber, String email) {
        this.userId = userId;
        this.userPw = userPw;
        this.nickName = nickName;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @OneToMany(mappedBy = "sellerId",fetch = FetchType.LAZY)
    private List<ProductSell> productSells = new ArrayList<>();
    @OneToMany(mappedBy = "buyerId",fetch = FetchType.LAZY)
    private List<AuctionBuy> auctionBuys = new ArrayList<>();

    @OneToMany(mappedBy = "bidderId",fetch = FetchType.LAZY)
    private List<AuctionComment> auctionComments = new ArrayList<>();
    public User(Long id) {
        this.id = id;
    }
    public  User(String nickName) {
        this.nickName = nickName;
    }
}
