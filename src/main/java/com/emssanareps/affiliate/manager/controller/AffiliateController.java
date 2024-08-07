package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.constants.AffiliateConstants;
import com.emssanareps.affiliate.manager.dto.request.AffiliateRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.AffiliateResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.AffiliateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/affiliate")
public class AffiliateController {

    private final AffiliateService affiliateService;

    @Autowired
    public AffiliateController(AffiliateService affiliateService) {
        this.affiliateService = affiliateService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<AffiliateResponse>> create(@Valid @RequestBody AffiliateRequest affiliateRequest) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateResponse>builder()
                        .data(affiliateService.create(affiliateRequest))
                        .status(HttpStatus.CREATED)
                        .message(AffiliateConstants.SUCCESS_REGISTER)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<Page<AffiliateResponse>>> all(@RequestBody(required = false) RequestDto<Object> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<AffiliateResponse>>builder()
                        .data(affiliateService.readAll(requestDto))
                        .status(HttpStatus.OK)
                        .message(AffiliateConstants.SUCCESS_OPERATION)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/r/{affiliateId}")
    public ResponseEntity<ResponseDto<AffiliateResponse>> readById(@PathVariable("affiliateId") Long affiliateId) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateResponse>builder()
                        .data(affiliateService.readById(affiliateId))
                        .status(HttpStatus.OK)
                        .message(AffiliateConstants.SUCCESS_OPERATION)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/u/{affiliateId}")
    public ResponseEntity<ResponseDto<AffiliateResponse>> modify(@PathVariable("affiliateId") Long affiliateId,
                                                                 @Valid @RequestBody AffiliateRequest affiliateRequest) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateResponse>builder()
                        .data(affiliateService.modify(affiliateId, affiliateRequest))
                        .status(HttpStatus.OK)
                        .message(AffiliateConstants.SUCCESS_MODIFY)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/d/{affiliateId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("affiliateId") Long affiliateId) {
        affiliateService.remove(affiliateId);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data(AffiliateConstants.DELETED)
                        .message(AffiliateConstants.SUCCESS_OPERATION)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<Page<AffiliateResponse>>> searchNameOrDescription(@RequestBody RequestDto<NameOrLastnameRequest> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<AffiliateResponse>>builder()
                        .data(affiliateService.searchNameOrDescription(requestDto))
                        .message(AffiliateConstants.SEARCH_RESULTS)
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
