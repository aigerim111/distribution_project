package com.example.demo.repositories;

import com.example.demo.entities.Catalogue;
import com.example.demo.entities.Organization;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CatalogueRepo extends JpaRepository<Catalogue, Long> {

    Optional<Catalogue> findAllByCatalogue_id(Long id);
    List<Catalogue> findAllByOrg(Organization org, Pageable pageable);

    //??

}
