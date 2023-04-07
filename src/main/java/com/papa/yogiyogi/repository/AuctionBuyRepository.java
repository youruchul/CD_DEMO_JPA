package com.papa.yogiyogi.repository;

import com.papa.yogiyogi.domain.dto.AuctionBuyDTO;
import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AuctionBuyRepository extends JpaRepository<AuctionBuy, Long> {
    Page<AuctionBuyDTO> findAllByOrderByIdDesc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE AuctionBuy ab SET ab.auctionStatus = :auctionStatus, ab.sellerId = :sellerId WHERE ab.id = :id")
    void updateAuctionBuyStatus(EAuctionStatus auctionStatus, Long sellerId, Long id);

    @Query(value = "select ab from AuctionBuy As ab " +
            "inner join ab.buyerId as u " +
            "on u.id = :id " +
            "order by ab.id desc")
    List<AuctionBuyDTO> findAllByBuyerId(@Param("id") Long buyerId);

    @Query(value = "select ab from AuctionBuy As ab where ab.auctionStatus = 'proceeding'")
    List<AuctionBuy> findAllByAuctionStatus();

//    @Query(value = "select ab, count(ac.id) as commentCount from AuctionBuy as ab " +
//            "left join AuctionComment ac " +
//            "on ac.auctionId.id = ab.id " +
//            "where ab.buyerId.id = :buyerId " +
//            "group by ab.id " +
//            "order by count(ac.id) desc"
//            )
//    Page<AuctionBuyDTO> findAllOrderByCountId(Pageable pageable, @Param("buyerId") Long buyerId);

}
