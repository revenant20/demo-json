package com.example.demojson.repository;

import com.example.demojson.entity.Attribute;
import com.example.demojson.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository
        extends JpaRepository<Product, String> {
    Optional<Product> findByAttributesContaining(Attribute attribute);

    @Query(value = """
    select
      p
    from
      Product p join p.attributes as atr
    where
          atr.attrValue = :attrValue
      and atr.attributeId.attrName = :attrName
    """)
    List<Product> findAllByAttrValueAndAttrName(
            @Param("attrValue") String attrValue,
            @Param("attrName") String attrName
    );

    @Query(value = """
    select
      p
    from
    """
    +  " Product p join fetch p.attributes as atr "
//    +  " Product p join p.attributes as atr "
    + """        
    where
          atr.attrValue = :attrValue
      and atr.attributeId.attrName = :attrName
    """)
    List<Product> findAllByAttrValueAndAttrNameJoinFetch(
            @Param("attrValue") String attrValue,
            @Param("attrName") String attrName
    );

    @Query(value = """
    select
      p
    from
    """
    +  " Product p join fetch p.attributes as atr "
//    +  " Product p join p.attributes as atr "
    + """        
    where
          atr.attrValue = :attrValue
      and atr.attributeId.attrName = :attrName
    """)
    List<Product> findAllByAttrValueAndAttrNameJoinFetchPageable(
            @Param("attrValue") String attrValue,
            @Param("attrName") String attrName,
            Pageable page
    );
}
