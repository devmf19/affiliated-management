package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrLastnameRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {
    private final BeneficiaryService beneficiaryService;

    @Autowired
    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }

    @PostMapping("/create/{affiliateId}")
    public ResponseEntity<ResponseDto<BeneficiaryResponse>> create(@PathVariable("affiliateId") Long affiliateId,
                                                                   @RequestBody BeneficiaryRequest beneficiaryRequest) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryResponse>builder()
                        .data(beneficiaryService.create(affiliateId, beneficiaryRequest))
                        .status(HttpStatus.CREATED)
                        .message("Registro exitoso")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<Page<BeneficiaryResponse>>> all(@RequestBody(required = false) RequestDto<Object> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<BeneficiaryResponse>>builder()
                        .data(beneficiaryService.readAll(requestDto))
                        .status(HttpStatus.OK)
                        .message("Operacion exitosa")
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/r/{beneficiaryId}")
    public ResponseEntity<ResponseDto<BeneficiaryResponse>> readById(@PathVariable("beneficiaryId") Long beneficiaryId) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryResponse>builder()
                        .data(beneficiaryService.readById(beneficiaryId))
                        .status(HttpStatus.OK)
                        .message("Operacion exitosa")
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/u/{beneficiaryId}")
    public ResponseEntity<ResponseDto<BeneficiaryResponse>> modify(@PathVariable("beneficiaryId") Long beneficiaryId,
                                                                 @RequestBody BeneficiaryRequest beneficiaryRequest) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryResponse>builder()
                        .data(beneficiaryService.modify(beneficiaryId, beneficiaryRequest))
                        .status(HttpStatus.OK)
                        .message("Modicicacion exitosa")
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/d/{beneficiaryId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("beneficiaryId") Long beneficiaryId) {
        beneficiaryService.remove(beneficiaryId);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data("Afiliado eliminado")
                        .message("Operacion existosa")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<Page<BeneficiaryResponse>>> searchNameOrDescription(RequestDto<NameOrLastnameRequest> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<BeneficiaryResponse>>builder()
                        .data(beneficiaryService.searchNameOrDescription(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
