package com.emssanareps.affiliate.manager.mapper;

import com.emssanareps.affiliate.manager.enums.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnumsMapper {
    String beneficiaryTypeToString(BeneficiaryType beneficiaryType);
    String civilStatusToString(CivilStatus civilStatus);
    String contactTypeToString(ContactType contactType);
    String documentTypeToString(DocumentType documentType);
    String genreToString(Genre genre);
    String regimeToString(Regime regime);
    String statusToString(Status status);
}
