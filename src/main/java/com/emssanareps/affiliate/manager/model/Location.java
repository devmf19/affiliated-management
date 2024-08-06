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

    @Column(name = "dane_municipality_id", nullable = false)
    private Long DaneMunicipalityId;

    @Column(name = "dane_department_id", nullable = false)
    private Long DaneDepartmentId;
}
