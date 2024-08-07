package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.constants.ContactConstants;
import com.emssanareps.affiliate.manager.dto.request.AffiliateContactRequest;
import com.emssanareps.affiliate.manager.dto.response.AffiliateContactResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/affiliate/contact")
public class AffiliateContactController {
    
    private final ContactService contactService;

    @Autowired
    public AffiliateContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create/{affiliateId}")
    public ResponseEntity<ResponseDto<AffiliateContactResponse>> create(@PathVariable("affiliateId") Long affiliateId,
                                                                        @Validated @RequestBody AffiliateContactRequest contactRequest) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateContactResponse>builder()
                        .data(contactService.create(affiliateId, contactRequest))
                        .status(HttpStatus.CREATED)
                        .message(ContactConstants.SUCCESS_REGISTER)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/u/{affiliateId}")
    public ResponseEntity<ResponseDto<AffiliateContactResponse>> modify(@PathVariable("affiliateId") Long affiliateId,
                                                                        @Validated @RequestBody AffiliateContactRequest contactRequest) {
        return new ResponseEntity<>(
                ResponseDto.<AffiliateContactResponse>builder()
                        .data(contactService.modify(affiliateId, contactRequest))
                        .status(HttpStatus.OK)
                        .message(ContactConstants.SUCCESS_MODIFY)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/d/{affiliateId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("affiliateId") Long affiliateId,
                                                      @Validated @RequestBody AffiliateContactRequest contactRequest) {
        contactService.remove(affiliateId, contactRequest);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data(ContactConstants.DELETED)
                        .status(HttpStatus.OK)
                        .message(ContactConstants.SUCCESS_OPERATION)
                        .build(),
                HttpStatus.OK
        );
    }
}
