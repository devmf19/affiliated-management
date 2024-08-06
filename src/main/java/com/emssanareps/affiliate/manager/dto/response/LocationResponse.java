package com.emssanareps.affiliate.manager.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationResponse {
    private Long id;
    private String address;
    private String description;
    private String municipality;
    private String department;
}
