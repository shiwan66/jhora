package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.service.MaskService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.MaskDTO;
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
 * REST controller for managing Mask.
 */
@RestController
@RequestMapping("/api")
public class MaskResource {

    private final Logger log = LoggerFactory.getLogger(MaskResource.class);

    private static final String ENTITY_NAME = "mask";

    private final MaskService maskService;

    public MaskResource(MaskService maskService) {
        this.maskService = maskService;
    }

    /**
     * POST  /masks : Create a new mask.
     *
     * @param maskDTO the maskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new maskDTO, or with status 400 (Bad Request) if the mask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/masks")
    @Timed
    public ResponseEntity<MaskDTO> createMask(@RequestBody MaskDTO maskDTO) throws URISyntaxException {
        log.debug("REST request to save Mask : {}", maskDTO);
        if (maskDTO.getId() != null) {
            throw new BadRequestAlertException("A new mask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaskDTO result = maskService.save(maskDTO);
        return ResponseEntity.created(new URI("/api/masks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /masks : Updates an existing mask.
     *
     * @param maskDTO the maskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated maskDTO,
     * or with status 400 (Bad Request) if the maskDTO is not valid,
     * or with status 500 (Internal Server Error) if the maskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/masks")
    @Timed
    public ResponseEntity<MaskDTO> updateMask(@RequestBody MaskDTO maskDTO) throws URISyntaxException {
        log.debug("REST request to update Mask : {}", maskDTO);
        if (maskDTO.getId() == null) {
            return createMask(maskDTO);
        }
        MaskDTO result = maskService.save(maskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, maskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /masks : get all the masks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of masks in body
     */
    @GetMapping("/masks")
    @Timed
    public ResponseEntity<List<MaskDTO>> getAllMasks(Pageable pageable) {
        log.debug("REST request to get a page of Masks");
        Page<MaskDTO> page = maskService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/masks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /masks/:id : get the "id" mask.
     *
     * @param id the id of the maskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the maskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/masks/{id}")
    @Timed
    public ResponseEntity<MaskDTO> getMask(@PathVariable Long id) {
        log.debug("REST request to get Mask : {}", id);
        MaskDTO maskDTO = maskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(maskDTO));
    }

    /**
     * DELETE  /masks/:id : delete the "id" mask.
     *
     * @param id the id of the maskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/masks/{id}")
    @Timed
    public ResponseEntity<Void> deleteMask(@PathVariable Long id) {
        log.debug("REST request to delete Mask : {}", id);
        maskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
