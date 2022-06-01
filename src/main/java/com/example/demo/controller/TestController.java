package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.services.TestService;
import com.example.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class TestController {

    private final TestService testService;
    private final UserService userService;

    public TestController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @PostMapping("/warehouse/{orgid}")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse,
                                                     @PathVariable("orgid") String orgId){
        return new ResponseEntity<>(testService.createWarehouse(warehouse, Long.parseLong(orgId)), HttpStatus.OK);
    }

    @PostMapping("/order/{orgid}/{userid}")
    public ResponseEntity<Order> createOrder(@PathVariable("orgid") String orgId,
                                             @PathVariable("userid") String userId){
        return new ResponseEntity<>(
                testService.createOrder(Long.parseLong(orgId),
                        Long.parseLong(userId)), HttpStatus.OK);
    }

    @GetMapping("/catalogue/{catalogueid}/products")
    public ResponseEntity<List<DictProduct>> getProductsByCatalogue(@PathVariable("catalogueid") String catalogueId){
        return new ResponseEntity<>(testService.findProductsByCatalogue(Long.parseLong(catalogueId)), HttpStatus.OK);
    }

//
//    @PostMapping("/product/{dictProductTypeId}")
//    public ResponseEntity<DictProduct> createProduct(
//            @RequestBody DictProduct product,
//            @PathVariable("dictProductTypeId") String productTypeId) throws IOException {
//        return new ResponseEntity<>(
//                testService.createProduct(product,
//                        Long.parseLong(productTypeId)), HttpStatus.OK);
//    }




//    @PostMapping("/login/{orgId}/{dictUserTypeId}")
//    public ResponseEntity<Login> loginUser(@RequestBody Login login, @PathVariable("orgId") String orgId, @PathVariable("dictUserTypeId") String dictUserTypeId){
//        return new ResponseEntity<>(loginService.createLogin(login, Long.parseLong(orgId), Long.parseLong(dictUserTypeId)), HttpStatus.OK);
//    }
//
//
//    @PostMapping("/user/{userid}")
//    public ResponseEntity<User> addUserData(@RequestBody UserDTO userDTO,
//                                           @PathVariable("userid") String userId){
//        return new ResponseEntity<>(
//                userService.addUserData(userDTO, Long.parseLong(userId)), HttpStatus.OK);
//    }

//    @GetMapping("/userlist")
//    public ResponseEntity<List<User>> allUsers(){
//        return new ResponseEntity<>(testService.allUsers(), HttpStatus.OK);
//    }

//    @PostMapping("/catalogue/{orgId}")
//    public ResponseEntity<Catalogue> createCatalogue(@RequestBody Catalogue catalogue,
//                                                     @PathVariable("orgId") String orgId){
//        return new ResponseEntity<>(testService.createCatalogue(catalogue, Long.parseLong(orgId)), HttpStatus.OK);
//    }

//    @PostMapping("/org")
//    public ResponseEntity<List<Organization>> createOrganization(@RequestBody List<Organization> organization){
//        return new ResponseEntity<>(testService.createOrganization(organization), HttpStatus.OK);
//    }

//    @PostMapping("/add/{catalogueId}/{productId}")
//    public ResponseEntity<Catalogue> addProductToCatalogue(@PathVariable("catalogueId") String catalogueId,
//                                                        @PathVariable("productId") String productId,
//                                                        @RequestParam("quantity") String quantity){
//        Catalogue catalogue = testService.addProductToCatalogue(
//                Long.parseLong(productId),
//                Long.parseLong(catalogueId),
//                Long.parseLong(quantity));
//        return new ResponseEntity<>(catalogue, HttpStatus.OK);
//    }

//    @GetMapping("/cataloguelist")
//    public ResponseEntity<List<Catalogue>> allCatalogues(){
//        return new ResponseEntity<>(testService.getCatalogues(), HttpStatus.OK);
//    }

}
