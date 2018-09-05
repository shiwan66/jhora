package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.DBFile;
import com.mycompany.myapp.service.DBFileService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DBFile.
 */
@RestController
@RequestMapping("/api")
public class DBFileResource {

    private final Logger log = LoggerFactory.getLogger(DBFileResource.class);

    private static final String ENTITY_NAME = "dBFile";

    private final DBFileService dBFileService;

    public DBFileResource(DBFileService dBFileService) {
        this.dBFileService = dBFileService;
    }

    /**
     * POST  /db-files : Create a new dBFile.
     *
     * @param dBFile the dBFile to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dBFile, or with status 400 (Bad Request) if the dBFile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/db-files")
    @Timed
    public ResponseEntity<DBFile> createDBFile(@RequestBody DBFile dBFile) throws URISyntaxException {
        log.debug("REST request to save DBFile : {}", dBFile);
        if (dBFile.getId() != null) {
            throw new BadRequestAlertException("A new dBFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DBFile result = dBFileService.save(dBFile);
        return ResponseEntity.created(new URI("/api/db-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /db-files : Updates an existing dBFile.
     *
     * @param dBFile the dBFile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dBFile,
     * or with status 400 (Bad Request) if the dBFile is not valid,
     * or with status 500 (Internal Server Error) if the dBFile couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/db-files")
    @Timed
    public ResponseEntity<DBFile> updateDBFile(@RequestBody DBFile dBFile) throws URISyntaxException {
        log.debug("REST request to update DBFile : {}", dBFile);
        if (dBFile.getId() == null) {
            return createDBFile(dBFile);
        }
        DBFile result = dBFileService.save(dBFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dBFile.getId().toString()))
            .body(result);
    }

    /**
     * GET  /db-files : get all the dBFiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dBFiles in body
     */
    @GetMapping("/db-files")
    @Timed
    public ResponseEntity<List<DBFile>> getAllDBFiles(Pageable pageable) {
        log.debug("REST request to get a page of DBFiles");
        Page<DBFile> page = dBFileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/db-files");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /db-files/:id : get the "id" dBFile.
     *
     * @param id the id of the dBFile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dBFile, or with status 404 (Not Found)
     */
    @GetMapping("/db-files/{id}")
    @Timed
    public ResponseEntity<DBFile> getDBFile(@PathVariable Long id) {
        log.debug("REST request to get DBFile : {}", id);
        DBFile dBFile = dBFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dBFile));
    }

    /**
     * DELETE  /db-files/:id : delete the "id" dBFile.
     *
     * @param id the id of the dBFile to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/db-files/{id}")
    @Timed
    public ResponseEntity<Void> deleteDBFile(@PathVariable Long id) {
        log.debug("REST request to delete DBFile : {}", id);
        dBFileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
