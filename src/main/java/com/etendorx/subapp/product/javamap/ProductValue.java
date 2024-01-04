package com.etendorx.subapp.product.javamap;

import com.etendorx.entities.jparepo.FinancialMgmtTaxCategoryRepository;
import com.etendorx.entities.jparepo.OrganizationRepository;
import com.etendorx.entities.jparepo.ProductCategoryRepository;
import com.etendorx.entities.jparepo.UOMRepository;
import com.etendorx.entities.mapper.lib.DTOWriteMapping;
import com.etendorx.entities.mappings.PRODSUBAPPM_ProductDTOWrite;
import org.apache.commons.lang3.StringUtils;
import org.openbravo.model.common.plm.Product;
import org.springframework.stereotype.Component;

@Component("PRODSUBAPPProductValueWrite")
public class ProductValue implements DTOWriteMapping<Product, PRODSUBAPPM_ProductDTOWrite> {

  private final OrganizationRepository organizationRepository;
  private final ProductCategoryRepository productCategoryRepository;
  private final UOMRepository uomRepository;
  private final FinancialMgmtTaxCategoryRepository financialMgmtTaxCategoryRepository;

  public ProductValue(OrganizationRepository organizationRepository,
      ProductCategoryRepository productCategoryRepository,
      FinancialMgmtTaxCategoryRepository financialMgmtTaxCategoryRepository,
      UOMRepository uomRepository) {
    this.organizationRepository = organizationRepository;
    this.productCategoryRepository = productCategoryRepository;
    this.financialMgmtTaxCategoryRepository = financialMgmtTaxCategoryRepository;
    this.uomRepository = uomRepository;
  }

  @Override
  public void map(Product entity, PRODSUBAPPM_ProductDTOWrite dto) {
    if (StringUtils.isEmpty(entity.getSearchKey())) {
      entity.setSearchKey("TEST " + Math.random());
    }
    if (StringUtils.isEmpty(entity.getDescription())) {
      entity.setDescription("default");
    }
    if (entity.getOrganization() == null) {
      entity.setOrganization(
          organizationRepository.findById("B843C30461EA4501935CB1D125C9C25A").orElse(null));
    }
    if (entity.getProductCategory() == null) {
      entity.setProductCategory(
          productCategoryRepository.findById("DC7F246D248B4C54BFC5744D5C27704F").orElse(null));
    }
    if (StringUtils.isEmpty(entity.getProductType())) {
      entity.setProductType("I");
    }
    if (entity.getTaxCategory() == null) {
      entity.setTaxCategory(
          financialMgmtTaxCategoryRepository.findById("E020A69A1E784DC39BE57C41D6D5DB4E")
              .orElse(null));
    }
    if (entity.getUOM() == null) {
      entity.setUOM(uomRepository.findById("100").orElse(null));
    }
  }
}
