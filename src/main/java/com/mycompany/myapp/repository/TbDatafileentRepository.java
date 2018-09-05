package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TbDatafileent;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TbDatafileent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TbDatafileentRepository extends JpaRepository<TbDatafileent, Long> {

}
