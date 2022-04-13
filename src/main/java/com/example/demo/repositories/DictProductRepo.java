package com.example.demo.repositories;

import com.example.demo.entities.DictProduct;
import com.example.demo.entities.DictProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DictProductRepo extends JpaRepository<DictProduct, Long> {

    Optional<DictProduct> findAllByProduct_id(Long id);
    List<DictProduct> findAllByDictProductType(DictProductType dictProductType, Pageable pageable);
    List<DictProduct> findAllByName_engLikeOrName_ruLikeOrnOrName_kzLike(String name);
    List<DictProduct> findAllByDescr_engLikeOrdesOrDescr_ruLikeOrnOrName_kz(String descr);
    List<DictProduct> findAllByProducer_engOrpOrProducer_ruOrpOrProducer_kz(String producer, Pageable pageable);
    List<DictProduct> findAllByOriginCountry_engOroOrOriginCountry_ruOroOrOriginCountry_kz(String name, Pageable pageable);

}
