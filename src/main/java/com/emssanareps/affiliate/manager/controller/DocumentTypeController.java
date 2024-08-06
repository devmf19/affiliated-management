package com.emssanareps.affiliate.manager.controller;

import com.emssanareps.affiliate.manager.dto.request.DocumentTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.DocumentTypeResponse;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import com.emssanareps.affiliate.manager.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document/type")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<DocumentTypeResponse>> create(@RequestBody DocumentTypeRequest documentTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<DocumentTypeResponse>builder()
                        .data(documentTypeService.create(documentTypeRequest))
                        .status(HttpStatus.CREATED)
                        .message("Registro exitoso")
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto<Page<DocumentTypeResponse>>> all(RequestDto<Object> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<DocumentTypeResponse>>builder()
                        .data(documentTypeService.readAll(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/r/{documentTypeId}")
    public ResponseEntity<ResponseDto<DocumentTypeResponse>> readById(@PathVariable("documentTypeId") Long documentTypeId) {
        return new ResponseEntity<>(
                ResponseDto.<DocumentTypeResponse>builder()
                        .data(documentTypeService.readById(documentTypeId))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping("/u/{documentTypeId}")
    public ResponseEntity<ResponseDto<DocumentTypeResponse>> modify(@PathVariable("documentTypeId") Long documentTypeId,
                                                                    @RequestBody DocumentTypeRequest documentTypeRequest) {
        return new ResponseEntity<>(
                ResponseDto.<DocumentTypeResponse>builder()
                        .data(documentTypeService.modify(documentTypeId, documentTypeRequest))
                        .message("Datos modificados")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/d/{documentTypeId}")
    public ResponseEntity<ResponseDto<String>> remove(@PathVariable("documentTypeId") Long documentTypeId) {
        documentTypeService.remove(documentTypeId);
        return new ResponseEntity<>(
                ResponseDto.<String>builder()
                        .data("Tipo de documento eliminado")
                        .message("Operacion existosa")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseDto<Page<DocumentTypeResponse>>> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> requestDto) {
        return new ResponseEntity<>(
                ResponseDto.<Page<DocumentTypeResponse>>builder()
                        .data(documentTypeService.searchNameOrDescription(requestDto))
                        .message("Resultados de búsqueda")
                        .status(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }
}
