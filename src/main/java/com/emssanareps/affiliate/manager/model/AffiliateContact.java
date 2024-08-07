package com.emssanareps.affiliate.manager.model;

import com.emssanareps.affiliate.manager.enums.ContactType;
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

    @Column(name = "contact_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType contactType;
}
