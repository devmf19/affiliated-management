package com.emssanareps.affiliate.manager.service;

import com.emssanareps.affiliate.manager.dto.request.ContactTypeRequest;
import com.emssanareps.affiliate.manager.dto.response.ContactTypeResponse;

import java.util.List;

public interface ContactTypeService {
    ContactTypeResponse create(ContactTypeRequest contactTypeRequest);

    List<ContactTypeResponse> readAll();

    ContactTypeResponse readById(Long contactTypeId);
}
