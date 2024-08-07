package com.emssanareps.affiliate.manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "dane_municipality_code", nullable = false)
    private Long DaneMunicipalityCode;

    @Column(name = "dane_department_code", nullable = false)
    private Long DaneDepartmentCode;
}
