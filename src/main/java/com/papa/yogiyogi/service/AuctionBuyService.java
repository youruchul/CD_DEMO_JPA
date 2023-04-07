package com.papa.yogiyogi.service;

import com.papa.yogiyogi.Exception.WrongCommentIdError;
import com.papa.yogiyogi.domain.dto.AuctionBuyDTO;
import com.papa.yogiyogi.domain.dto.EAuctionStatus;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import com.papa.yogiyogi.domain.response.InsertAuctionBuyResponse;
import com.papa.yogiyogi.domain.response.ViewAuctionBuyListResponse;
import com.papa.yogiyogi.domain.response.ViewDetailAuctionBuyResponse;
import com.papa.yogiyogi.repository.AuctionBuyRepository;
import com.papa.yogiyogi.repository.AuctionCommentRepository;
import com.papa.yogiyogi.security.SecurityService;
import com.papa.yogiyogi.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuctionBuyService {
    private final AuctionBuyRepository auctionBuyRepository;
    private final SecurityService securityService;

    private final AuctionCommentRepository auctionCommentRepository;

    // 1. 등록
    public InsertAuctionBuyResponse insertAuctionBuy (AuctionBuyDTO dto) {

        AuctionBuy auctionBuy = new AuctionBuy(dto);
        auctionBuyRepository.save(auctionBuy);
        return new InsertAuctionBuyResponse(auctionBuy);
    }
    // 2.모든 등록된 경매 리스트 보기
    public List<ViewAuctionBuyListResponse> findAllAuctionBuy(Pageable pageable) {
        List<ViewAuctionBuyListResponse> viewAuctionBuyListResponses = new ArrayList<>();
        Page<AuctionBuyDTO> all = auctionBuyRepository.findAllByOrderByIdDesc(pageable);
        for (AuctionBuyDTO one: all) {
            Optional<AuctionBuy> auctionBuy = auctionBuyRepository.findById(one.getId());
            one.setCommentCount(auctionCommentRepository.findCountByAuctionId(auctionBuy.get().getId()));
            viewAuctionBuyListResponses.add(new ViewAuctionBuyListResponse(
                    one.getId(),
                    one.getTitle(),
                    one.getBuyerNickName(),
                    one.getCategory(),
                    one.getMinCondition(),
                    one.getHighWishPrice(),
                    one.getTimeout(),
                    one.getAuctionStatus(),
                    one.getCommentCount()
            ));
        }
        return viewAuctionBuyListResponses;
    }
    // 3.하나의 경매의 세부정보 확인
    public ViewDetailAuctionBuyResponse findDetailAuctionBuy(Long id) {
        Optional<AuctionBuy> byId = auctionBuyRepository.findById(id);
        Integer commentCount = auctionCommentRepository.findCountByAuctionId(byId.get().getId());
        return new ViewDetailAuctionBuyResponse(byId.get(), commentCount);
    }
    // 4. 경매가 판매완료시 update 로 수정
    public  String updateBuy (Long id, Long commentId) throws WrongCommentIdError{
        Optional<AuctionComment>auctionComment = auctionCommentRepository.findById(commentId);
        if (!Objects.equals(id, auctionComment.get().getAuctionId().getId())) {
            throw new WrongCommentIdError();
        }
        auctionBuyRepository.updateAuctionBuyStatus(EAuctionStatus.DEAL_SUCCESS, auctionComment.get().getBidderId().getId(), id);
        return "판매 완료";
    }
    // 4-1. 경매가 시간이 지났는데 판매완료가 되지 않음
    @Scheduled (fixedRate = 60000)
    public void updateBuyByTime () {
        List<AuctionBuy> all = auctionBuyRepository.findAllByAuctionStatus();
        for (AuctionBuy one : all) {

            Long longTimeOut = one.getTimeout().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            Long longNow = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            System.out.println(longTimeOut);
            System.out.println(longNow);
            System.out.println(one.getTimeout());
            System.out.println(one.getAuctionStatus());
            if (longNow>longTimeOut) {

                auctionBuyRepository.updateAuctionBuyStatus(EAuctionStatus.FINISHED, null, one.getId());
            }
        }
        System.out.println("schedule");

    }
    // 5. 나의 경매목록 보기
    public List<ViewAuctionBuyListResponse> viewMyAuctionBuy( ) {
        List<ViewAuctionBuyListResponse> viewMyAuctionBuyListResponses = new ArrayList<>();
        TokenInfo token = securityService.parseToken(securityService.getToken());
        Long myId = token.getId();
        List<AuctionBuyDTO> all = auctionBuyRepository.findAllByBuyerId(myId);
        for (AuctionBuyDTO one: all) {
            Optional<AuctionBuy> auctionBuy = auctionBuyRepository.findById(one.getId());
            one.setCommentCount(auctionCommentRepository.findCountByAuctionId(auctionBuy.get().getId()));
            viewMyAuctionBuyListResponses.add(new ViewAuctionBuyListResponse(
                    one.getId(),
                    one.getTitle(),
                    one.getBuyerNickName(),
                    one.getCategory(),
                    one.getMinCondition(),
                    one.getHighWishPrice(),
                    one.getTimeout(),
                    one.getAuctionStatus(),
                    one.getCommentCount()
            ));
            System.out.println(viewMyAuctionBuyListResponses + "!!!!!!!!!!!");
            ///for push
        }
        return viewMyAuctionBuyListResponses;
    }
    // 6. 경매 삭제
    public String deleteAuctionById(Long id) {
        auctionBuyRepository.deleteById(id);
        return "경매 삭제 완료";
    }



}
