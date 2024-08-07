package com.emssanareps.affiliate.manager.model;

import com.emssanareps.affiliate.manager.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "affiliate")
@Getter
@Setter
@NoArgsConstructor
public class Affiliate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number", unique = true, nullable = false)
    private Long identificationNumber;

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

    @Column(name = "civil_satus", nullable = false)
    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;

    @Column(name = "regime", nullable = false)
    @Enumerated(EnumType.STRING)
    private Regime regime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "document_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "affiliate_id")
    List<AffiliateContact> contacts =  new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "affiliate_id")
    List<Beneficiary> beneficiaries =  new ArrayList<>();
}
