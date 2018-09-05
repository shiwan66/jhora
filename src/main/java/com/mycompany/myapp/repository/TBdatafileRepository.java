package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TBdatafile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TBdatafile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TBdatafileRepository extends JpaRepository<TBdatafile, Long> {

}
