package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.zip.Deflater;

@Service
public class TestService {

    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final CatalogueRepo catalogueRepo;
    private final OrganizationRepo organizationRepo;
    private final DictProductRepo productRepo;
    private final WarehouseRepo warehouseRepo;
    private final OrderRepo orderRepo;

    public TestService(UserRepo userRepo, LoginRepo loginRepo, CatalogueRepo catalogueRepo, OrganizationRepo organizationRepo, DictProductRepo productRepo, WarehouseRepo warehouseRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.catalogueRepo = catalogueRepo;
        this.organizationRepo = organizationRepo;
        this.productRepo = productRepo;
        this.warehouseRepo = warehouseRepo;
        this.orderRepo = orderRepo;
    }

    public Login loginUser(UserDTO userDTO) {
        Login loginUser = new Login();
        loginUser.setUsername(userDTO.getUsername());
        loginUser.setPassword(userDTO.getPassword());
        return loginRepo.save(loginUser);
    }

    public User createUser(User user, Long loginId, Long orgId) {
        Login login = loginRepo.findById(loginId)
                .orElseThrow();
        Organization org = organizationRepo.findById(orgId)
                .orElseThrow();
        user.setLogin(login);
        user.setOrg(org);
        return userRepo.save(user);
    }

    public List<User> allUsers() {
        return userRepo.findAll();
    }

    public Catalogue createCatalogue(Catalogue catalogue) {
        return catalogueRepo.save(catalogue);
    }

    public Catalogue addProductToCatalogue(Long productId, Long catalogueId, Long quantity){
        DictProduct product = productRepo.findByProductId(productId).orElseThrow();
        Catalogue catalogue = catalogueRepo.findByCatalogueId(catalogueId).orElseThrow();
        CatalogueProducts catalogueProducts = new CatalogueProducts(catalogue,product,quantity);

        catalogue.getCatalogueProducts().add(catalogueProducts);
        return catalogueRepo.save(catalogue); //kak ispravit'
    }

    public List<Organization> createOrganization(List<Organization> organization) {
        return organizationRepo.saveAll(organization);
    }

    public DictProduct createProduct(DictProduct product) throws IOException {
        return productRepo.save(product);
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepo.save(warehouse);
    }

    public Order createOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Catalogue> getCatalogues(){
        return catalogueRepo.findAll();
    }
}
