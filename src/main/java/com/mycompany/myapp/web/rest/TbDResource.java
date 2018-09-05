package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.TbD;
import com.mycompany.myapp.service.TbDService;
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
 * REST controller for managing TbD.
 */
@RestController
@RequestMapping("/api")
public class TbDResource {

    private final Logger log = LoggerFactory.getLogger(TbDResource.class);

    private static final String ENTITY_NAME = "tbD";

    private final TbDService tbDService;

    public TbDResource(TbDService tbDService) {
        this.tbDService = tbDService;
    }

    /**
     * POST  /tb-ds : Create a new tbD.
     *
     * @param tbD the tbD to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tbD, or with status 400 (Bad Request) if the tbD has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tb-ds")
    @Timed
    public ResponseEntity<TbD> createTbD(@RequestBody TbD tbD) throws URISyntaxException {
        log.debug("REST request to save TbD : {}", tbD);
        if (tbD.getId() != null) {
            throw new BadRequestAlertException("A new tbD cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TbD result = tbDService.save(tbD);
        return ResponseEntity.created(new URI("/api/tb-ds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tb-ds : Updates an existing tbD.
     *
     * @param tbD the tbD to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tbD,
     * or with status 400 (Bad Request) if the tbD is not valid,
     * or with status 500 (Internal Server Error) if the tbD couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tb-ds")
    @Timed
    public ResponseEntity<TbD> updateTbD(@RequestBody TbD tbD) throws URISyntaxException {
        log.debug("REST request to update TbD : {}", tbD);
        if (tbD.getId() == null) {
            return createTbD(tbD);
        }
        TbD result = tbDService.save(tbD);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tbD.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tb-ds : get all the tbDS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tbDS in body
     */
    @GetMapping("/tb-ds")
    @Timed
    public ResponseEntity<List<TbD>> getAllTbDS(Pageable pageable) {
        log.debug("REST request to get a page of TbDS");
        Page<TbD> page = tbDService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tb-ds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tb-ds/:id : get the "id" tbD.
     *
     * @param id the id of the tbD to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tbD, or with status 404 (Not Found)
     */
    @GetMapping("/tb-ds/{id}")
    @Timed
    public ResponseEntity<TbD> getTbD(@PathVariable Long id) {
        log.debug("REST request to get TbD : {}", id);
        TbD tbD = tbDService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tbD));
    }

    /**
     * DELETE  /tb-ds/:id : delete the "id" tbD.
     *
     * @param id the id of the tbD to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tb-ds/{id}")
    @Timed
    public ResponseEntity<Void> deleteTbD(@PathVariable Long id) {
        log.debug("REST request to delete TbD : {}", id);
        tbDService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
