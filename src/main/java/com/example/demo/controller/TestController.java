package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.*;
import com.example.demo.services.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/login")
    public ResponseEntity<Login> loginUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(testService.loginUser(userDTO), HttpStatus.OK);
    }

    @PostMapping("/user/{loginId}/{orgId}")
    public ResponseEntity<User> createUser(@RequestBody User user,
                                           @PathVariable("loginId") String loginId,
                                           @PathVariable("orgId") String orgId){
        return new ResponseEntity<>(
                testService.createUser(user, Long.parseLong(loginId), Long.parseLong(orgId)), HttpStatus.OK);
    }

    @GetMapping("/userlist")
    public ResponseEntity<List<User>> allUsers(){
        return new ResponseEntity<>(testService.allUsers(), HttpStatus.OK);
    }

    @PostMapping("/catalogue")
    public ResponseEntity<Catalogue> createCatalogue(@RequestBody Catalogue catalogue){
        return new ResponseEntity<>(testService.createCatalogue(catalogue), HttpStatus.OK);
    }

    @PostMapping("/org")
    public ResponseEntity<List<Organization>> createOrganization(@RequestBody List<Organization> organization){
        return new ResponseEntity<>(testService.createOrganization(organization), HttpStatus.OK);
    }

    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){
        return new ResponseEntity<>(testService.createWarehouse(warehouse), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(testService.createOrder(order), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<DictProduct> createProduct(@RequestBody DictProduct product) throws IOException {
        return new ResponseEntity<>(testService.createProduct(product), HttpStatus.OK);
    }

    @PostMapping("/add/{catalogueId}/{productId}")
    public ResponseEntity<Catalogue> addProductToCatalogue(@PathVariable("catalogueId") String catalogueId,
                                                        @PathVariable("productId") String productId,
                                                        @RequestParam("quantity") String quantity){
        Catalogue catalogue = testService.addProductToCatalogue(
                Long.parseLong(productId),
                Long.parseLong(catalogueId),
                Long.parseLong(quantity));
        return new ResponseEntity<>(catalogue, HttpStatus.OK);
    }

    @GetMapping("/cataloguelist")
    public ResponseEntity<List<Catalogue>> allCatalogues(){
        return new ResponseEntity<>(testService.getCatalogues(), HttpStatus.OK);
    }

}
