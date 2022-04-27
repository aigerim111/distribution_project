package com.example.demo.controller;

import com.example.demo.entities.Catalogue;
import com.example.demo.services.CatalogueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CatalogueController {

    private final CatalogueService catalogueService;

    public CatalogueController(CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @PostMapping("/catalogue/{orgId}")
    public ResponseEntity<Catalogue> createCatalogue(@RequestBody Catalogue catalogue,
                                                     @PathVariable("orgId") String orgId){
        return new ResponseEntity<>(catalogueService.createCatalogue(catalogue, Long.parseLong(orgId)), HttpStatus.OK);
    }

    @PostMapping("/add/{catalogueId}/{productId}")
    public ResponseEntity<Catalogue> addProductToCatalogue(@PathVariable("catalogueId") String catalogueId,
                                                           @PathVariable("productId") String productId,
                                                           @RequestParam("quantity") String quantity){
        Catalogue catalogue = catalogueService.addProductToCatalogue(
                Long.parseLong(productId),
                Long.parseLong(catalogueId),
                Long.parseLong(quantity));
        return new ResponseEntity<>(catalogue, HttpStatus.OK);
    }

    @GetMapping("/cataloguelist")
    public ResponseEntity<List<Catalogue>> allCatalogues(){
        return new ResponseEntity<>(catalogueService.getCatalogues(), HttpStatus.OK);
    }
}
