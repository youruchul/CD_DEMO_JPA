package com.papa.yogiyogi.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.papa.yogiyogi.domain.dto.ProductSellDTO;
import com.papa.yogiyogi.domain.entity.ProductSell;
import com.papa.yogiyogi.domain.request.ProductSearchRequest;
import com.papa.yogiyogi.domain.response.InsertProductSellResponse;
import com.papa.yogiyogi.domain.response.ViewDetailProductSellResponse;
import com.papa.yogiyogi.domain.response.ViewProductSellListResponse;
import com.papa.yogiyogi.repository.ProductSellRepository;
import com.papa.yogiyogi.repository.UserRepository;
import com.papa.yogiyogi.security.SecurityService;
import com.papa.yogiyogi.security.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductSellService {
    private final ProductSellRepository productSellRepository;
    private final SecurityService securityService;
    private final FireBaseService fireBaseService;
    private final UserRepository userRepository;

    public InsertProductSellResponse insertProductSell(ProductSellDTO dto) throws IOException, FirebaseAuthException {
        String myImgPath = fireBaseService.uploadFiles(dto.getFile(), dto.getNameFile());
        ProductSell productSell = new ProductSell(dto, myImgPath);
        productSellRepository.save(productSell);
        return new InsertProductSellResponse(productSell);

    }

    public List<ViewProductSellListResponse> findAllProductSell(Pageable pageable) {
        List<ViewProductSellListResponse> viewProductSellListResponses = new ArrayList<>();
        Page<ProductSell> all = productSellRepository.findAllByOrderByIdDesc(pageable);
        for (ProductSell one : all) {
            viewProductSellListResponses.add(new ViewProductSellListResponse(
                    one.getId(),
                    one.getTitle(),
                    one.getUrl(),
                    one.getSellerId().getNickName(),
                    one.getCategory(),
                    one.getPrice(),
                    one.getBuyerNickName(),
                    one.getIsSold()
            ));

        }
        return viewProductSellListResponses;
    }

    public ViewDetailProductSellResponse findDetailProductSell(Long id) {
        Optional<ProductSell> byId = productSellRepository.findById(id);
        return new ViewDetailProductSellResponse(byId.get());

    }

    public String updateSold(Long id) {
        Optional<ProductSell> productSell = productSellRepository.findById(id);
        String buyerNickName = securityService.parseToken(securityService.getToken()).getNickName();
        if (productSell.isEmpty()) {
            return "존재하지 않는 게시글입니다";
        } else {
            productSell.get().setBuyerNickName(buyerNickName);
            productSell.get().setIsSold(true);
        }
        productSellRepository.save(productSell.get());
        return "판매완료";
    }

    public List<ViewProductSellListResponse> viewMyProductSell() {
        List<ViewProductSellListResponse> viewMyProductSellListResponses = new ArrayList<>();
        TokenInfo token = securityService.parseToken(securityService.getToken());
        Long myId = token.getId();
        List<ProductSell> all = productSellRepository.findAllBySellerId(myId);
        for (ProductSell one : all) {
            viewMyProductSellListResponses.add(new ViewProductSellListResponse(
                    one.getId(),
                    one.getTitle(),
                    one.getUrl(),
                    one.getSellerId().getNickName(),
                    one.getCategory(),
                    one.getPrice(),
                    one.getBuyerNickName(),
                    one.getIsSold()
            ));
        }
        return viewMyProductSellListResponses;
    }

    public String deleteById(Long id) {
        productSellRepository.deleteById(id);
        return "삭제완료";

    }

    // search
    public List<ViewProductSellListResponse> findLikeByTitle(ProductSearchRequest request) {
        System.out.println(request.getTitle() + "services");
        List <ViewProductSellListResponse> responses = new ArrayList<>();
        List<ProductSell> all = productSellRepository.findLikeByTitle(request.getTitle());
        for (ProductSell one: all) {
            responses.add( new ViewProductSellListResponse(
                    one.getId(),
                    one.getTitle(),
                    one.getUrl(),
                    one.getSellerId().getNickName(),
                    one.getCategory(),
                    one.getPrice(),
                    one.getBuyerNickName(),
                    one.getIsSold()

            ));
        }
        return responses;
    }
}
