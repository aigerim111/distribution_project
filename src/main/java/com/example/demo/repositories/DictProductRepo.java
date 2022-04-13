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

    Optional<DictProduct> findByProductId(Long id);
    List<DictProduct> findAllByDictProductType(DictProductType dictProductType, Pageable pageable);

    List<DictProduct> findAllByNameEngContainingIgnoreCase(String nameEng);
    List<DictProduct> findAllByNameRuContainingIgnoreCase(String nameRu);
    List<DictProduct> findAllByNameKzContainingIgnoreCase(String nameKz);

    List<DictProduct> findAllByDescrEngContainingIgnoreCase(String descrEng);
    List<DictProduct> findAllByDescrRuContainingIgnoreCase(String descrRu);
    List<DictProduct> findAllByDescrKzContainingIgnoreCase(String descrKz);

    List<DictProduct> findAllByProducerEngContainingIgnoreCase(String ProducerEng);
    List<DictProduct> findAllByProducerRuContainingIgnoreCase(String ProducerRu);
    List<DictProduct> findAllByProducerKzContainingIgnoreCase(String ProducerKz);

    List<DictProduct> findAllByOriginCountryEngContainingIgnoreCase(String OriginCountryEng);
    List<DictProduct> findAllByOriginCountryRuContainingIgnoreCase(String OriginCountryRu);
    List<DictProduct> findAllByOriginCountryKzContainingIgnoreCase(String OriginCountryKz);

//    List<DictProduct> findAllByDescrEngContOrDescrRuLikeOrDescrKzLike(String descrEng, String descrRu, String descrKZ);
//    List<DictProduct> findAllByProducerEngOrProducerRuOrProducer_kz(String producer, Pageable pageable);
//    List<DictProduct> findAllByOriginCountry_engOrOriginCountry_ruOrOriginCountry_kz(String name, Pageable pageable);

}
