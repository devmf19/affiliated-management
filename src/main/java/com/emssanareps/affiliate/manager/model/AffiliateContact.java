package com.emssanareps.affiliate.manager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "affiliate_contact")
@Getter
@Setter
@NoArgsConstructor
public class AffiliateContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "affiliate_id", nullable = false)
    private Long affiliateId;

    @Column(name = "contact", nullable = false)
    private String contact;

    @ManyToOne
    @JoinColumn(name = "contact_type_id", nullable = false)
    private ContactType contactType;
}
