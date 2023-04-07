package com.papa.yogiyogi.repository;

import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import com.papa.yogiyogi.domain.entity.ProductSell;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuctionCommentRepository extends JpaRepository<AuctionComment,Long> {
    Page<AuctionComment> findAllByOrderByBiddingPriceAsc(Pageable pageable);
    
    @Query("select count(ac.id) as commentCount from AuctionComment As ac where ac.auctionId.id = :auctionId")
    Integer findCountByAuctionId(@Param("auctionId") Long auctionBuy);

}
