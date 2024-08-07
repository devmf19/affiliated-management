package com.emssanareps.affiliate.manager.model;

import com.emssanareps.affiliate.manager.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "beneficiary")
@Getter
@Setter
@NoArgsConstructor
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number", unique = true, nullable = false)
    private Long identificationNumber;

    @Column(name = "affiliate_id", nullable = false)
    private Long affiliateId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "affiliation_date", nullable = false)
    private LocalDate affiliation_date;

    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "document_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @Column(name = "beneficiary_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BeneficiaryType beneficiaryType;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}
