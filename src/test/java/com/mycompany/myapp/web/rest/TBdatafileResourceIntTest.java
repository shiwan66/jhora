package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.TBdatafile;
import com.mycompany.myapp.repository.TBdatafileRepository;
import com.mycompany.myapp.service.TBdatafileService;
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
 * Test class for the TBdatafileResource REST controller.
 *
 * @see TBdatafileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class TBdatafileResourceIntTest {

    private static final String DEFAULT_TB_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TB_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TB_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TB_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private TBdatafileRepository tBdatafileRepository;

    @Autowired
    private TBdatafileService tBdatafileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTBdatafileMockMvc;

    private TBdatafile tBdatafile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TBdatafileResource tBdatafileResource = new TBdatafileResource(tBdatafileService);
        this.restTBdatafileMockMvc = MockMvcBuilders.standaloneSetup(tBdatafileResource)
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
    public static TBdatafile createEntity(EntityManager em) {
        TBdatafile tBdatafile = new TBdatafile()
            .tbFileName(DEFAULT_TB_FILE_NAME)
            .tbFileType(DEFAULT_TB_FILE_TYPE);
        return tBdatafile;
    }

    @Before
    public void initTest() {
        tBdatafile = createEntity(em);
    }

    @Test
    @Transactional
    public void createTBdatafile() throws Exception {
        int databaseSizeBeforeCreate = tBdatafileRepository.findAll().size();

        // Create the TBdatafile
        restTBdatafileMockMvc.perform(post("/api/t-bdatafiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tBdatafile)))
            .andExpect(status().isCreated());

        // Validate the TBdatafile in the database
        List<TBdatafile> tBdatafileList = tBdatafileRepository.findAll();
        assertThat(tBdatafileList).hasSize(databaseSizeBeforeCreate + 1);
        TBdatafile testTBdatafile = tBdatafileList.get(tBdatafileList.size() - 1);
        assertThat(testTBdatafile.getTbFileName()).isEqualTo(DEFAULT_TB_FILE_NAME);
        assertThat(testTBdatafile.getTbFileType()).isEqualTo(DEFAULT_TB_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createTBdatafileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tBdatafileRepository.findAll().size();

        // Create the TBdatafile with an existing ID
        tBdatafile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTBdatafileMockMvc.perform(post("/api/t-bdatafiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tBdatafile)))
            .andExpect(status().isBadRequest());

        // Validate the TBdatafile in the database
        List<TBdatafile> tBdatafileList = tBdatafileRepository.findAll();
        assertThat(tBdatafileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTBdatafiles() throws Exception {
        // Initialize the database
        tBdatafileRepository.saveAndFlush(tBdatafile);

        // Get all the tBdatafileList
        restTBdatafileMockMvc.perform(get("/api/t-bdatafiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tBdatafile.getId().intValue())))
            .andExpect(jsonPath("$.[*].tbFileName").value(hasItem(DEFAULT_TB_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].tbFileType").value(hasItem(DEFAULT_TB_FILE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTBdatafile() throws Exception {
        // Initialize the database
        tBdatafileRepository.saveAndFlush(tBdatafile);

        // Get the tBdatafile
        restTBdatafileMockMvc.perform(get("/api/t-bdatafiles/{id}", tBdatafile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tBdatafile.getId().intValue()))
            .andExpect(jsonPath("$.tbFileName").value(DEFAULT_TB_FILE_NAME.toString()))
            .andExpect(jsonPath("$.tbFileType").value(DEFAULT_TB_FILE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTBdatafile() throws Exception {
        // Get the tBdatafile
        restTBdatafileMockMvc.perform(get("/api/t-bdatafiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTBdatafile() throws Exception {
        // Initialize the database
        tBdatafileService.save(tBdatafile);

        int databaseSizeBeforeUpdate = tBdatafileRepository.findAll().size();

        // Update the tBdatafile
        TBdatafile updatedTBdatafile = tBdatafileRepository.findOne(tBdatafile.getId());
        // Disconnect from session so that the updates on updatedTBdatafile are not directly saved in db
        em.detach(updatedTBdatafile);
        updatedTBdatafile
            .tbFileName(UPDATED_TB_FILE_NAME)
            .tbFileType(UPDATED_TB_FILE_TYPE);

        restTBdatafileMockMvc.perform(put("/api/t-bdatafiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTBdatafile)))
            .andExpect(status().isOk());

        // Validate the TBdatafile in the database
        List<TBdatafile> tBdatafileList = tBdatafileRepository.findAll();
        assertThat(tBdatafileList).hasSize(databaseSizeBeforeUpdate);
        TBdatafile testTBdatafile = tBdatafileList.get(tBdatafileList.size() - 1);
        assertThat(testTBdatafile.getTbFileName()).isEqualTo(UPDATED_TB_FILE_NAME);
        assertThat(testTBdatafile.getTbFileType()).isEqualTo(UPDATED_TB_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTBdatafile() throws Exception {
        int databaseSizeBeforeUpdate = tBdatafileRepository.findAll().size();

        // Create the TBdatafile

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTBdatafileMockMvc.perform(put("/api/t-bdatafiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tBdatafile)))
            .andExpect(status().isCreated());

        // Validate the TBdatafile in the database
        List<TBdatafile> tBdatafileList = tBdatafileRepository.findAll();
        assertThat(tBdatafileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTBdatafile() throws Exception {
        // Initialize the database
        tBdatafileService.save(tBdatafile);

        int databaseSizeBeforeDelete = tBdatafileRepository.findAll().size();

        // Get the tBdatafile
        restTBdatafileMockMvc.perform(delete("/api/t-bdatafiles/{id}", tBdatafile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TBdatafile> tBdatafileList = tBdatafileRepository.findAll();
        assertThat(tBdatafileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TBdatafile.class);
        TBdatafile tBdatafile1 = new TBdatafile();
        tBdatafile1.setId(1L);
        TBdatafile tBdatafile2 = new TBdatafile();
        tBdatafile2.setId(tBdatafile1.getId());
        assertThat(tBdatafile1).isEqualTo(tBdatafile2);
        tBdatafile2.setId(2L);
        assertThat(tBdatafile1).isNotEqualTo(tBdatafile2);
        tBdatafile1.setId(null);
        assertThat(tBdatafile1).isNotEqualTo(tBdatafile2);
    }
}
