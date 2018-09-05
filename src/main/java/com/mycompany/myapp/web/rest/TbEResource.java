package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.TbEService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.TbEDTO;
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
 * REST controller for managing TbE.
 */
@RestController
@RequestMapping("/api")
public class TbEResource {

    private final Logger log = LoggerFactory.getLogger(TbEResource.class);

    private static final String ENTITY_NAME = "tbE";

    private final TbEService tbEService;

    public TbEResource(TbEService tbEService) {
        this.tbEService = tbEService;
    }

    /**
     * POST  /tb-es : Create a new tbE.
     *
     * @param tbEDTO the tbEDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tbEDTO, or with status 400 (Bad Request) if the tbE has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tb-es")
    @Timed
    public ResponseEntity<TbEDTO> createTbE(@RequestBody TbEDTO tbEDTO) throws URISyntaxException {
        log.debug("REST request to save TbE : {}", tbEDTO);
        if (tbEDTO.getId() != null) {
            throw new BadRequestAlertException("A new tbE cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TbEDTO result = tbEService.save(tbEDTO);
        return ResponseEntity.created(new URI("/api/tb-es/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tb-es : Updates an existing tbE.
     *
     * @param tbEDTO the tbEDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tbEDTO,
     * or with status 400 (Bad Request) if the tbEDTO is not valid,
     * or with status 500 (Internal Server Error) if the tbEDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tb-es")
    @Timed
    public ResponseEntity<TbEDTO> updateTbE(@RequestBody TbEDTO tbEDTO) throws URISyntaxException {
        log.debug("REST request to update TbE : {}", tbEDTO);
        if (tbEDTO.getId() == null) {
            return createTbE(tbEDTO);
        }
        TbEDTO result = tbEService.save(tbEDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tbEDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tb-es : get all the tbES.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tbES in body
     */
    @GetMapping("/tb-es")
    @Timed
    public ResponseEntity<List<TbEDTO>> getAllTbES(Pageable pageable) {
        log.debug("REST request to get a page of TbES");
        Page<TbEDTO> page = tbEService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tb-es");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tb-es/:id : get the "id" tbE.
     *
     * @param id the id of the tbEDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tbEDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tb-es/{id}")
    @Timed
    public ResponseEntity<TbEDTO> getTbE(@PathVariable Long id) {
        log.debug("REST request to get TbE : {}", id);
        TbEDTO tbEDTO = tbEService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tbEDTO));
    }

    /**
     * DELETE  /tb-es/:id : delete the "id" tbE.
     *
     * @param id the id of the tbEDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tb-es/{id}")
    @Timed
    public ResponseEntity<Void> deleteTbE(@PathVariable Long id) {
        log.debug("REST request to delete TbE : {}", id);
        tbEService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
