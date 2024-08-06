package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.request.NameOrDescriptionRequest;
import com.emssanareps.affiliate.manager.dto.request.RequestDto;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactTypeService {
    ContactTypeResponse create(ContactTypeRequest contactTypeRequest);

    Page<ContactTypeResponse> readAll(RequestDto<Object> requestDto);

    ContactTypeResponse readById(Long contactTypeId);

    ContactTypeResponse modify(Long contactTypeId, ContactTypeRequest contactTypeRequest);

    void remove(Long contactTypeId);

    Page<ContactTypeResponse> searchNameOrDescription(RequestDto<NameOrDescriptionRequest> contactTypeRequest);
}
