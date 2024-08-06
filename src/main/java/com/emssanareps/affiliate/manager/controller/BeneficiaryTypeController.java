package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.dto.request.BeneficiaryTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.BeneficiaryTypeResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.BeneficiaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiary/type")
public class BeneficiaryTypeController {
    private final BeneficiaryTypeService beneficiaryTypeService;

    @Autowired
    public BeneficiaryTypeController(BeneficiaryTypeService beneficiaryTypeService) {
        this.beneficiaryTypeService = beneficiaryTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<BeneficiaryTypeResponse>> create(@RequestBody BeneficiaryTypeRequest beneficiaryTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryTypeResponse>builder()
                        .data(beneficiaryTypeService.create(beneficiaryTypeRequest))
                        .status(HttpStatus.CREATED)
                        .message("Registro exitoso")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<Page<BeneficiaryTypeResponse>>> all(RequestDto<Object> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<BeneficiaryTypeResponse>>builder()
                        .data(beneficiaryTypeService.readAll(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/r/{beneficiaryTypeId}")
    public ResponseEntity<ResponseDto<BeneficiaryTypeResponse>> readById(@PathVariable("beneficiaryTypeId") Long beneficiaryTypeId) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryTypeResponse>builder()
                        .data(beneficiaryTypeService.readById(beneficiaryTypeId))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/u/{beneficiaryTypeId}")
    public ResponseEntity<ResponseDto<BeneficiaryTypeResponse>> modify(@PathVariable("beneficiaryTypeId") Long beneficiaryTypeId,
                                                                    @RequestBody BeneficiaryTypeRequest beneficiaryTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<BeneficiaryTypeResponse>builder()
                        .data(beneficiaryTypeService.modify(beneficiaryTypeId, beneficiaryTypeRequest))
                        .message("Datos modificados")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/d/{beneficiaryTypeId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("beneficiaryTypeId") Long beneficiaryTypeId) {
        beneficiaryTypeService.remove(beneficiaryTypeId);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data("Tipo de beneficiaryo eliminado")
                        .message("Operacion existosa")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<Page<BeneficiaryTypeResponse>>> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<BeneficiaryTypeResponse>>builder()
                        .data(beneficiaryTypeService.searchNameOrDescription(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
