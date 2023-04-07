package com.papa.yogiyogi.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.papa.yogiyogi.Exception.WrongCommentIdError;
import com.papa.yogiyogi.domain.dto.AuctionCommentDTO;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionBuy;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import com.papa.yogiyogi.domain.entity.User;
import com.papa.yogiyogi.domain.response.*;
import com.papa.yogiyogi.repository.AuctionCommentRepository;
import com.papa.yogiyogi.repository.UserRepository;
import com.papa.yogiyogi.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class AuctionCommentService {
    private final AuctionCommentRepository auctionCommentRepository;
    private final SecurityService securityService;
    private final FireBaseService fireBaseService;
    private final UserRepository userRepository;

    // 1. 등록
    public InsertAuctionCommentResponse insertAuctionComment (AuctionCommentDTO dto) throws IOException, FirebaseAuthException {
        String myImgPath = fireBaseService.uploadFiles(dto.getFile(),dto.getNameFile());
        Optional <User> findById = userRepository.findById(dto.getBidderId());
        User user = findById.orElseThrow(NullPointerException::new);

        AuctionComment auctionComment = new AuctionComment(dto , myImgPath, user);

        auctionCommentRepository.save(auctionComment);
        return new InsertAuctionCommentResponse(auctionComment);

    }
    public List<ViewAuctionCommentListResponse> viewAllComment (Pageable pageable, Long auctionId) {
        List<ViewAuctionCommentListResponse> auctionCommentListResponses = new ArrayList<>();
        Page<AuctionComment> all = auctionCommentRepository.findAllByOrderByBiddingPriceAsc(pageable);
        for (AuctionComment one: all) {
            if(Objects.equals(one.getAuctionId().getId(), auctionId)) {
                auctionCommentListResponses.add(new ViewAuctionCommentListResponse(
                        one.getId(),
                        one.getAuctionId().getId(),
                        one.getBidderId().getNickName(),
                        one.getTitle(),
                        one.getPCondition(),
                        one.getBiddingPrice(),
                        one.getAuctionStatus()
                ));
            }
        }
        return auctionCommentListResponses;
    }
    public ViewDetailAuctionCommentResponse viewDetailComment (Long commentId, Long id) throws WrongCommentIdError {
        Optional<AuctionComment> byId = auctionCommentRepository.findById(commentId);
        if (!Objects.equals(id, byId.get().getAuctionId().getId())) {
            throw new WrongCommentIdError();
        }
        return new ViewDetailAuctionCommentResponse(byId.get());
    }

    public String deleteAuctionCommentById (Long id) {
        userRepository.deleteById(id);
        return "코멘트 삭제 완료";
    }


}
