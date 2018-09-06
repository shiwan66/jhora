package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data JPA repository for the DBFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Long> {

}
