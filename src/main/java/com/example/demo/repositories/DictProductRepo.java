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

    Optional<DictProduct> findByProduct_id(Long id);
    List<DictProduct> findAllByDictProductType(DictProductType dictProductType, Pageable pageable);
    List<DictProduct> findAllByName_engLikeOrName_ruLikeOrName_kzLike(String name);
    List<DictProduct> findAllByDescr_engLikeOrDescr_ruLikeOrDescr_kzLike(String descr);
    List<DictProduct> findAllByProducer_engOrProducer_ruOrProducer_kz(String producer, Pageable pageable);
    List<DictProduct> findAllByOriginCountry_engOrOriginCountry_ruOrOriginCountry_kz(String name, Pageable pageable);

}
