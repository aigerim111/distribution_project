package com.example.demo.services;

import com.example.demo.entities.Catalogue;
import com.example.demo.entities.CatalogueProducts;
import com.example.demo.entities.DictProduct;
import com.example.demo.entities.Organization;
import com.example.demo.repositories.CatalogueRepo;
import com.example.demo.repositories.DictProductRepo;
import com.example.demo.repositories.OrganizationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogueService {

    private final CatalogueRepo catalogueRepo;
    private final OrganizationRepo organizationRepo;
    private final DictProductRepo productRepo;

    public CatalogueService(CatalogueRepo catalogueRepo, OrganizationRepo organizationRepo, DictProductRepo productRepo) {
        this.catalogueRepo = catalogueRepo;
        this.organizationRepo = organizationRepo;
        this.productRepo = productRepo;
    }

    public Catalogue createCatalogue(Long orgId) {
        Catalogue catalogue = new Catalogue();
        Organization org = organizationRepo.findByOrgId(orgId).orElseThrow();
        catalogue.setOrg(org);
        return catalogueRepo.save(catalogue);
    }

    public Catalogue addProductToCatalogue(Long productId, Long catalogueId, Long quantity){
        DictProduct product = productRepo.findByProductId(productId).orElseThrow();
        Catalogue catalogue = catalogueRepo.findByCatalogueId(catalogueId).orElseThrow();
        CatalogueProducts catalogueProducts = new CatalogueProducts(catalogue,product,quantity);

        catalogue.getCatalogueProducts().add(catalogueProducts);
        return catalogueRepo.save(catalogue);
    }

    public List<Catalogue> getCatalogues(){
        return catalogueRepo.findAll();
    }

}
