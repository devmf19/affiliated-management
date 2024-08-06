package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.ContactTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact/type")
public class ContactTypeController {
    private final ContactTypeService contactTypeService;

    @Autowired
    public ContactTypeController(ContactTypeService contactTypeService) {
        this.contactTypeService = contactTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<ContactTypeResponse>> create(@RequestBody ContactTypeRequest contactTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<ContactTypeResponse>builder()
                        .data(contactTypeService.create(contactTypeRequest))
                        .status(HttpStatus.CREATED)
                        .message("Registro exitoso")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<Page<ContactTypeResponse>>> all(RequestDto<Object> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<ContactTypeResponse>>builder()
                        .data(contactTypeService.readAll(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/r/{contactTypeId}")
    public ResponseEntity<ResponseDto<ContactTypeResponse>> readById(@PathVariable("contactTypeId") Long contactTypeId) {
        return new ResponseEntity<>(
                ResponseDto.<ContactTypeResponse>builder()
                        .data(contactTypeService.readById(contactTypeId))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/u/{contactTypeId}")
    public ResponseEntity<ResponseDto<ContactTypeResponse>> modify(@PathVariable("contactTypeId") Long contactTypeId,
                                                                    @RequestBody ContactTypeRequest contactTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<ContactTypeResponse>builder()
                        .data(contactTypeService.modify(contactTypeId, contactTypeRequest))
                        .message("Datos modificados")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/d/{contactTypeId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("contactTypeId") Long contactTypeId) {
        contactTypeService.remove(contactTypeId);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data("Tipo de contacto eliminado")
                        .message("Operacion existosa")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<Page<ContactTypeResponse>>> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<ContactTypeResponse>>builder()
                        .data(contactTypeService.searchNameOrDescription(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
