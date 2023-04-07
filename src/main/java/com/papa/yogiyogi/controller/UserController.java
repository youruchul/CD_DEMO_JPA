package com.papa.yogiyogi.controller;

import com.papa.yogiyogi.Exception.IdCheckException;
import com.papa.yogiyogi.Exception.LoginException;
import com.papa.yogiyogi.domain.request.LoginRequest;
import com.papa.yogiyogi.domain.request.SignupRequest;
import com.papa.yogiyogi.domain.response.UserResponse;
import com.papa.yogiyogi.domain.response.ViewAuctionBuyListResponse;
import com.papa.yogiyogi.domain.response.ViewProductSellListResponse;
import com.papa.yogiyogi.repository.UserRepository;
import com.papa.yogiyogi.security.SecurityService;
import com.papa.yogiyogi.security.TokenInfo;
import com.papa.yogiyogi.service.AuctionBuyService;
import com.papa.yogiyogi.service.ProductSellService;
import com.papa.yogiyogi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final ProductSellService productSellService;
    private final AuctionBuyService auctionBuyService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginRequest request) throws LoginException {
        return userService.loginService(request);
    }
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignupRequest request) throws IdCheckException, LoginException {
        userService.signupService(request);
    }

    @GetMapping("/me")
    public TokenInfo me(){
        String token = securityService.getToken();
        return securityService.parseToken(token);
    }
    @GetMapping("/me/selling")
    public List<ViewProductSellListResponse> viewMySellingProduct( ) {
        return productSellService.viewMyProductSell();
    }

    @GetMapping("/me/buying")
    public List<ViewAuctionBuyListResponse> viewMyBuyingAuction() {
        return auctionBuyService.viewMyAuctionBuy();
    }
}
