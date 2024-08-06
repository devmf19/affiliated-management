package com.emssanareps.affiliate.manager.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NameOrDescriptionRequest {
    private String name = "";
    private String description = "";
}
