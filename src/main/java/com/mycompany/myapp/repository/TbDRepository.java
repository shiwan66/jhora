package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TbD;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TbD entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TbDRepository extends JpaRepository<TbD, Long> {

}
