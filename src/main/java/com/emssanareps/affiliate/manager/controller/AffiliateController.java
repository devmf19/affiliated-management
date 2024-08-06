package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.AffiliateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AffiliateController {

    private final AffiliateService affiliateService;

    @Autowired
    public AffiliateController(AffiliateService affiliateService) {
        this.affiliateService = affiliateService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<AffiliateResponse>> create(@RequestBody AffiliateRequest affiliateRequest) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateResponse>builder()
                        .data(affiliateService.create(affiliateRequest))
                        .status(HttpStatus.CREATED)
                        .message("Registro exitoso")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<List<AffiliateResponse>>> all() {
        return new ResponseEntity<>(
                ResponseDto.<List<AffiliateResponse>>builder()
                        .data(affiliateService.readAll())
                        .status(HttpStatus.OK)
                        .message("Operacion exitosa")
                        .build(),
                HttpStatus.OK
        );
    }
}
