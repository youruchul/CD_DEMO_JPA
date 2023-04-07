package com.papa.yogiyogi.repository;

import com.papa.yogiyogi.domain.entity.ProductSell;
import com.papa.yogiyogi.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductSellRepository extends JpaRepository<ProductSell, Long> {
    Page<ProductSell> findAllByOrderByIdDesc(Pageable pageable);

    @Query("select p from ProductSell As p " +
            "inner join p.sellerId as u on u.id = :id " +
            "order by p.id desc")
    List<ProductSell> findAllBySellerId(@Param("id") Long sellerId);

    @Query("select p from ProductSell as p " +
            "where p.title like %:title%")
    List<ProductSell> findLikeByTitle(@Param("title") String title);



}
