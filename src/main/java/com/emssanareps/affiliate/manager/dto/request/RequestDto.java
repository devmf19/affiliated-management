package com.emssanareps.affiliate.manager.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestDto <T>{
    private int pageNumber = 1;
    private int rowsNumber = 10;
    private T data;
}
