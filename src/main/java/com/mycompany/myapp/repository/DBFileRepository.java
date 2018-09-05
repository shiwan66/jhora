package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DBFile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DBFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

}
