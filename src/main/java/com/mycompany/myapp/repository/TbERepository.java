package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TbE;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TbE entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TbERepository extends JpaRepository<TbE, Long> {

}
