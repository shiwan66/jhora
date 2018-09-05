package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.TbD;
import com.mycompany.myapp.repository.TbDRepository;
import com.mycompany.myapp.service.TbDService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TbDResource REST controller.
 *
 * @see TbDResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class TbDResourceIntTest {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private TbDRepository tbDRepository;

    @Autowired
    private TbDService tbDService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTbDMockMvc;

    private TbD tbD;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TbDResource tbDResource = new TbDResource(tbDService);
        this.restTbDMockMvc = MockMvcBuilders.standaloneSetup(tbDResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TbD createEntity(EntityManager em) {
        TbD tbD = new TbD()
            .fileName(DEFAULT_FILE_NAME)
            .fileType(DEFAULT_FILE_TYPE);
        return tbD;
    }

    @Before
    public void initTest() {
        tbD = createEntity(em);
    }

    @Test
    @Transactional
    public void createTbD() throws Exception {
        int databaseSizeBeforeCreate = tbDRepository.findAll().size();

        // Create the TbD
        restTbDMockMvc.perform(post("/api/tb-ds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbD)))
            .andExpect(status().isCreated());

        // Validate the TbD in the database
        List<TbD> tbDList = tbDRepository.findAll();
        assertThat(tbDList).hasSize(databaseSizeBeforeCreate + 1);
        TbD testTbD = tbDList.get(tbDList.size() - 1);
        assertThat(testTbD.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testTbD.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createTbDWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tbDRepository.findAll().size();

        // Create the TbD with an existing ID
        tbD.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTbDMockMvc.perform(post("/api/tb-ds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbD)))
            .andExpect(status().isBadRequest());

        // Validate the TbD in the database
        List<TbD> tbDList = tbDRepository.findAll();
        assertThat(tbDList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTbDS() throws Exception {
        // Initialize the database
        tbDRepository.saveAndFlush(tbD);

        // Get all the tbDList
        restTbDMockMvc.perform(get("/api/tb-ds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tbD.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTbD() throws Exception {
        // Initialize the database
        tbDRepository.saveAndFlush(tbD);

        // Get the tbD
        restTbDMockMvc.perform(get("/api/tb-ds/{id}", tbD.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tbD.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTbD() throws Exception {
        // Get the tbD
        restTbDMockMvc.perform(get("/api/tb-ds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTbD() throws Exception {
        // Initialize the database
        tbDService.save(tbD);

        int databaseSizeBeforeUpdate = tbDRepository.findAll().size();

        // Update the tbD
        TbD updatedTbD = tbDRepository.findOne(tbD.getId());
        // Disconnect from session so that the updates on updatedTbD are not directly saved in db
        em.detach(updatedTbD);
        updatedTbD
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE);

        restTbDMockMvc.perform(put("/api/tb-ds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTbD)))
            .andExpect(status().isOk());

        // Validate the TbD in the database
        List<TbD> tbDList = tbDRepository.findAll();
        assertThat(tbDList).hasSize(databaseSizeBeforeUpdate);
        TbD testTbD = tbDList.get(tbDList.size() - 1);
        assertThat(testTbD.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testTbD.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTbD() throws Exception {
        int databaseSizeBeforeUpdate = tbDRepository.findAll().size();

        // Create the TbD

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTbDMockMvc.perform(put("/api/tb-ds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbD)))
            .andExpect(status().isCreated());

        // Validate the TbD in the database
        List<TbD> tbDList = tbDRepository.findAll();
        assertThat(tbDList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTbD() throws Exception {
        // Initialize the database
        tbDService.save(tbD);

        int databaseSizeBeforeDelete = tbDRepository.findAll().size();

        // Get the tbD
        restTbDMockMvc.perform(delete("/api/tb-ds/{id}", tbD.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TbD> tbDList = tbDRepository.findAll();
        assertThat(tbDList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TbD.class);
        TbD tbD1 = new TbD();
        tbD1.setId(1L);
        TbD tbD2 = new TbD();
        tbD2.setId(tbD1.getId());
        assertThat(tbD1).isEqualTo(tbD2);
        tbD2.setId(2L);
        assertThat(tbD1).isNotEqualTo(tbD2);
        tbD1.setId(null);
        assertThat(tbD1).isNotEqualTo(tbD2);
    }
}
