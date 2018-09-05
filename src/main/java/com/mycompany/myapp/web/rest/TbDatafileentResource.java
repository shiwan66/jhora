package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.TbDatafileent;
import com.mycompany.myapp.service.TbDatafileentService;
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
 * REST controller for managing TbDatafileent.
 */
@RestController
@RequestMapping("/api")
public class TbDatafileentResource {

    private final Logger log = LoggerFactory.getLogger(TbDatafileentResource.class);

    private static final String ENTITY_NAME = "tbDatafileent";

    private final TbDatafileentService tbDatafileentService;

    public TbDatafileentResource(TbDatafileentService tbDatafileentService) {
        this.tbDatafileentService = tbDatafileentService;
    }

    /**
     * POST  /tb-datafileents : Create a new tbDatafileent.
     *
     * @param tbDatafileent the tbDatafileent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tbDatafileent, or with status 400 (Bad Request) if the tbDatafileent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tb-datafileents")
    @Timed
    public ResponseEntity<TbDatafileent> createTbDatafileent(@RequestBody TbDatafileent tbDatafileent) throws URISyntaxException {
        log.debug("REST request to save TbDatafileent : {}", tbDatafileent);
        if (tbDatafileent.getId() != null) {
            throw new BadRequestAlertException("A new tbDatafileent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TbDatafileent result = tbDatafileentService.save(tbDatafileent);
        return ResponseEntity.created(new URI("/api/tb-datafileents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tb-datafileents : Updates an existing tbDatafileent.
     *
     * @param tbDatafileent the tbDatafileent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tbDatafileent,
     * or with status 400 (Bad Request) if the tbDatafileent is not valid,
     * or with status 500 (Internal Server Error) if the tbDatafileent couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tb-datafileents")
    @Timed
    public ResponseEntity<TbDatafileent> updateTbDatafileent(@RequestBody TbDatafileent tbDatafileent) throws URISyntaxException {
        log.debug("REST request to update TbDatafileent : {}", tbDatafileent);
        if (tbDatafileent.getId() == null) {
            return createTbDatafileent(tbDatafileent);
        }
        TbDatafileent result = tbDatafileentService.save(tbDatafileent);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tbDatafileent.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tb-datafileents : get all the tbDatafileents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tbDatafileents in body
     */
    @GetMapping("/tb-datafileents")
    @Timed
    public ResponseEntity<List<TbDatafileent>> getAllTbDatafileents(Pageable pageable) {
        log.debug("REST request to get a page of TbDatafileents");
        Page<TbDatafileent> page = tbDatafileentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tb-datafileents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tb-datafileents/:id : get the "id" tbDatafileent.
     *
     * @param id the id of the tbDatafileent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tbDatafileent, or with status 404 (Not Found)
     */
    @GetMapping("/tb-datafileents/{id}")
    @Timed
    public ResponseEntity<TbDatafileent> getTbDatafileent(@PathVariable Long id) {
        log.debug("REST request to get TbDatafileent : {}", id);
        TbDatafileent tbDatafileent = tbDatafileentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tbDatafileent));
    }

    /**
     * DELETE  /tb-datafileents/:id : delete the "id" tbDatafileent.
     *
     * @param id the id of the tbDatafileent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tb-datafileents/{id}")
    @Timed
    public ResponseEntity<Void> deleteTbDatafileent(@PathVariable Long id) {
        log.debug("REST request to delete TbDatafileent : {}", id);
        tbDatafileentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
