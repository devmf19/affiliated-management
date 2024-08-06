package com.emssanareps.affiliate.manager.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestDto <T>{
    private int pageNumber;
    private int rowsNumber;
    private T data;
}
