package com.papa.yogiyogi.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.papa.yogiyogi.Exception.WrongCommentIdError;
import com.papa.yogiyogi.domain.dto.AuctionBuyDTO;
import com.papa.yogiyogi.domain.dto.AuctionCommentDTO;
import com.papa.yogiyogi.domain.dto.ECategory;
import com.papa.yogiyogi.domain.dto.ECondition;
import com.papa.yogiyogi.domain.entity.AuctionComment;
import com.papa.yogiyogi.domain.request.InsertAuctionBuyRequest;
import com.papa.yogiyogi.domain.request.InsertAuctionCommentRequest;
import com.papa.yogiyogi.domain.request.InsertProductSellRequest;
import com.papa.yogiyogi.domain.response.*;
import com.papa.yogiyogi.security.SecurityService;
import com.papa.yogiyogi.security.TokenInfo;
import com.papa.yogiyogi.service.AuctionBuyService;
import com.papa.yogiyogi.service.AuctionCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionBuyController {
    private final AuctionBuyService auctionBuyService;
    private final AuctionCommentService auctionCommentService;
    private final SecurityService securityService;

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public InsertAuctionBuyResponse insertAuctionBuy (@RequestBody InsertAuctionBuyRequest request) {
        TokenInfo token = securityService.parseToken(securityService.getToken());
        AuctionBuyDTO dto = new AuctionBuyDTO(token, request);
        return auctionBuyService.insertAuctionBuy(dto);
    }

    @GetMapping
    public List<ViewAuctionBuyListResponse> findAllAuctionBuy(Pageable pageable) {
        return auctionBuyService.findAllAuctionBuy(pageable);
    }

    @GetMapping("/{id}")
    public ViewDetailAuctionBuyResponse findDetailAuctionBuy (@PathVariable Long id) {
        return auctionBuyService.findDetailAuctionBuy(id);
    }
    @DeleteMapping("/{id} ")
    public String deleteCommentById (@PathVariable Long id) {
        return auctionBuyService.deleteAuctionById(id);
    }

    @PostMapping("/{id}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public InsertAuctionCommentResponse insertAuctionComment
            ( @ModelAttribute InsertAuctionCommentRequest request, @PathVariable Long id) throws IOException, FirebaseAuthException {
        System.out.println(request);
        System.out.println(request.getNameFile() + "namefile");
        TokenInfo tokenInfo = securityService.parseToken(securityService.getToken());
        AuctionCommentDTO dto = new AuctionCommentDTO(tokenInfo,request,id);
        System.out.println(tokenInfo.getNickName());
        System.out.println(dto);

        return auctionCommentService.insertAuctionComment(dto);
    }
    @GetMapping("/{id}/comment")
    public  List <ViewAuctionCommentListResponse> findAllCommentById (@PathVariable Long id, Pageable pageable) {
        return auctionCommentService.viewAllComment(pageable, id);
    }

    @GetMapping("/{id}/comment/{commentId}")
    public ViewDetailAuctionCommentResponse viewDetailComment (@PathVariable Long commentId, @PathVariable Long id) throws WrongCommentIdError {
        return auctionCommentService.viewDetailComment(commentId ,id);
    }

    @PutMapping("/{id}/comment/{commentId}")
    public String updateAuctionStatus (@PathVariable Long id, @PathVariable Long commentId) throws WrongCommentIdError {

        return auctionBuyService.updateBuy(id, commentId);
    }
    @DeleteMapping("/{id}/comment/{commentId}")
    public String deleteCommentById (@PathVariable Long id, @PathVariable Long commentId) {
        return auctionCommentService.deleteAuctionCommentById(commentId);
    }


}
