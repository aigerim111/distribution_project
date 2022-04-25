package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TestService {

    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;
    private final DictProductRepo productRepo;
    private final DictProductTypeRepo productTypeRepo;
    private final WarehouseRepo warehouseRepo;
    private final OrderRepo orderRepo;

    public TestService(UserRepo userRepo, OrganizationRepo organizationRepo, DictProductRepo productRepo, DictProductTypeRepo productTypeRepo, WarehouseRepo warehouseRepo, OrderRepo orderRepo) {
        this.userRepo = userRepo;
        this.organizationRepo = organizationRepo;
        this.productRepo = productRepo;
        this.productTypeRepo = productTypeRepo;
        this.warehouseRepo = warehouseRepo;
        this.orderRepo = orderRepo;
    }

//    public Login loginUser(UserDTO userDTO) {
//        Login loginUser = new Login();
//        loginUser.setUsername(userDTO.getUsername());
//        loginUser.setPassword(userDTO.getPassword());
//        return loginRepo.save(loginUser);
//    }

//    public User createUser(User user, Long loginId, Long orgId) {
//        Login login = loginRepo.findById(loginId)
//                .orElseThrow();
//        Organization org = organizationRepo.findById(orgId)
//                .orElseThrow();
//        user.setLogin(login);
//        user.setOrg(org);
//        return userRepo.save(user);
//    }

//    public List<User> allUsers() {
//        return userRepo.findAll();
//    }
//
//    public Catalogue createCatalogue(Catalogue catalogue, Long orgId) {
//        Organization org = organizationRepo.findByOrgId(orgId).orElseThrow();
//        catalogue.setOrg(org);
//        return catalogueRepo.save(catalogue);
//    }

//    public Catalogue addProductToCatalogue(Long productId, Long catalogueId, Long quantity){
//        DictProduct product = productRepo.findByProductId(productId).orElseThrow();
//        Catalogue catalogue = catalogueRepo.findByCatalogueId(catalogueId).orElseThrow();
//        CatalogueProducts catalogueProducts = new CatalogueProducts(catalogue,product,quantity);
//
//        catalogue.getCatalogueProducts().add(catalogueProducts);
//        return catalogueRepo.save(catalogue); //ispravili ura
//    }

//    public Organization createOrganization(Organization organization) {
//        return organizationRepo.saveAll(organization);
//    }

    public DictProduct createProduct(DictProduct product, Long productTypeId) throws IOException {
        DictProductType productType = productTypeRepo.findById(productTypeId).orElseThrow();
        product.setDictProductType(productType);

        return productRepo.save(product);
    }

    public Warehouse createWarehouse(Warehouse warehouse, Long orgId) {
        Organization org = organizationRepo.findByOrgId(orgId).orElseThrow();
        warehouse.setOrg(org);

        return warehouseRepo.save(warehouse);
    }

    public Order createOrder(Long orgId, Long userId) {
        Order order = new Order();
        User user = userRepo.findByUserId(userId).orElseThrow();
        Organization org = organizationRepo.findByOrgId(orgId).orElseThrow();
        order.setShopUserId(user);
        order.setShopOrgId(org);

        OrderProducts orderProducts = new OrderProducts();
        orderProducts.setOrder(order);
        order.getOrderProducts().add(orderProducts);

        return orderRepo.save(order);
    }
}
