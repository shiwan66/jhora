package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.TBdatafile;
import com.mycompany.myapp.service.TBdatafileService;
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
 * REST controller for managing TBdatafile.
 */
@RestController
@RequestMapping("/api")
public class TBdatafileResource {

    private final Logger log = LoggerFactory.getLogger(TBdatafileResource.class);

    private static final String ENTITY_NAME = "tBdatafile";

    private final TBdatafileService tBdatafileService;

    public TBdatafileResource(TBdatafileService tBdatafileService) {
        this.tBdatafileService = tBdatafileService;
    }

    /**
     * POST  /t-bdatafiles : Create a new tBdatafile.
     *
     * @param tBdatafile the tBdatafile to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tBdatafile, or with status 400 (Bad Request) if the tBdatafile has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-bdatafiles")
    @Timed
    public ResponseEntity<TBdatafile> createTBdatafile(@RequestBody TBdatafile tBdatafile) throws URISyntaxException {
        log.debug("REST request to save TBdatafile : {}", tBdatafile);
        if (tBdatafile.getId() != null) {
            throw new BadRequestAlertException("A new tBdatafile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TBdatafile result = tBdatafileService.save(tBdatafile);
        return ResponseEntity.created(new URI("/api/t-bdatafiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-bdatafiles : Updates an existing tBdatafile.
     *
     * @param tBdatafile the tBdatafile to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tBdatafile,
     * or with status 400 (Bad Request) if the tBdatafile is not valid,
     * or with status 500 (Internal Server Error) if the tBdatafile couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-bdatafiles")
    @Timed
    public ResponseEntity<TBdatafile> updateTBdatafile(@RequestBody TBdatafile tBdatafile) throws URISyntaxException {
        log.debug("REST request to update TBdatafile : {}", tBdatafile);
        if (tBdatafile.getId() == null) {
            return createTBdatafile(tBdatafile);
        }
        TBdatafile result = tBdatafileService.save(tBdatafile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tBdatafile.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-bdatafiles : get all the tBdatafiles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tBdatafiles in body
     */
    @GetMapping("/t-bdatafiles")
    @Timed
    public ResponseEntity<List<TBdatafile>> getAllTBdatafiles(Pageable pageable) {
        log.debug("REST request to get a page of TBdatafiles");
        Page<TBdatafile> page = tBdatafileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/t-bdatafiles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /t-bdatafiles/:id : get the "id" tBdatafile.
     *
     * @param id the id of the tBdatafile to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tBdatafile, or with status 404 (Not Found)
     */
    @GetMapping("/t-bdatafiles/{id}")
    @Timed
    public ResponseEntity<TBdatafile> getTBdatafile(@PathVariable Long id) {
        log.debug("REST request to get TBdatafile : {}", id);
        TBdatafile tBdatafile = tBdatafileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tBdatafile));
    }

    /**
     * DELETE  /t-bdatafiles/:id : delete the "id" tBdatafile.
     *
     * @param id the id of the tBdatafile to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-bdatafiles/{id}")
    @Timed
    public ResponseEntity<Void> deleteTBdatafile(@PathVariable Long id) {
        log.debug("REST request to delete TBdatafile : {}", id);
        tBdatafileService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
