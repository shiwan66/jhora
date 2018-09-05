package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.DBFile;
import com.mycompany.myapp.repository.DBFileRepository;
import com.mycompany.myapp.service.DBFileService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DBFileResource REST controller.
 *
 * @see DBFileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class DBFileResourceIntTest {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATA = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATA_CONTENT_TYPE = "image/png";

    @Autowired
    private DBFileRepository dBFileRepository;

    @Autowired
    private DBFileService dBFileService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDBFileMockMvc;

    private DBFile dBFile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DBFileResource dBFileResource = new DBFileResource(dBFileService);
        this.restDBFileMockMvc = MockMvcBuilders.standaloneSetup(dBFileResource)
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
    public static DBFile createEntity(EntityManager em) {
        DBFile dBFile = new DBFile()
            .fileName(DEFAULT_FILE_NAME)
            .fileType(DEFAULT_FILE_TYPE)
            .data(DEFAULT_DATA)
            .dataContentType(DEFAULT_DATA_CONTENT_TYPE);
        return dBFile;
    }

    @Before
    public void initTest() {
        dBFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createDBFile() throws Exception {
        int databaseSizeBeforeCreate = dBFileRepository.findAll().size();

        // Create the DBFile
        restDBFileMockMvc.perform(post("/api/db-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dBFile)))
            .andExpect(status().isCreated());

        // Validate the DBFile in the database
        List<DBFile> dBFileList = dBFileRepository.findAll();
        assertThat(dBFileList).hasSize(databaseSizeBeforeCreate + 1);
        DBFile testDBFile = dBFileList.get(dBFileList.size() - 1);
        assertThat(testDBFile.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testDBFile.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
        assertThat(testDBFile.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testDBFile.getDataContentType()).isEqualTo(DEFAULT_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createDBFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dBFileRepository.findAll().size();

        // Create the DBFile with an existing ID
        dBFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDBFileMockMvc.perform(post("/api/db-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dBFile)))
            .andExpect(status().isBadRequest());

        // Validate the DBFile in the database
        List<DBFile> dBFileList = dBFileRepository.findAll();
        assertThat(dBFileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDBFiles() throws Exception {
        // Initialize the database
        dBFileRepository.saveAndFlush(dBFile);

        // Get all the dBFileList
        restDBFileMockMvc.perform(get("/api/db-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dBFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dataContentType").value(hasItem(DEFAULT_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATA))));
    }

    @Test
    @Transactional
    public void getDBFile() throws Exception {
        // Initialize the database
        dBFileRepository.saveAndFlush(dBFile);

        // Get the dBFile
        restDBFileMockMvc.perform(get("/api/db-files/{id}", dBFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dBFile.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()))
            .andExpect(jsonPath("$.dataContentType").value(DEFAULT_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.data").value(Base64Utils.encodeToString(DEFAULT_DATA)));
    }

    @Test
    @Transactional
    public void getNonExistingDBFile() throws Exception {
        // Get the dBFile
        restDBFileMockMvc.perform(get("/api/db-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDBFile() throws Exception {
        // Initialize the database
        dBFileService.save(dBFile);

        int databaseSizeBeforeUpdate = dBFileRepository.findAll().size();

        // Update the dBFile
        DBFile updatedDBFile = dBFileRepository.findOne(dBFile.getId());
        // Disconnect from session so that the updates on updatedDBFile are not directly saved in db
        em.detach(updatedDBFile);
        updatedDBFile
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE)
            .data(UPDATED_DATA)
            .dataContentType(UPDATED_DATA_CONTENT_TYPE);

        restDBFileMockMvc.perform(put("/api/db-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDBFile)))
            .andExpect(status().isOk());

        // Validate the DBFile in the database
        List<DBFile> dBFileList = dBFileRepository.findAll();
        assertThat(dBFileList).hasSize(databaseSizeBeforeUpdate);
        DBFile testDBFile = dBFileList.get(dBFileList.size() - 1);
        assertThat(testDBFile.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testDBFile.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
        assertThat(testDBFile.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testDBFile.getDataContentType()).isEqualTo(UPDATED_DATA_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDBFile() throws Exception {
        int databaseSizeBeforeUpdate = dBFileRepository.findAll().size();

        // Create the DBFile

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDBFileMockMvc.perform(put("/api/db-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dBFile)))
            .andExpect(status().isCreated());

        // Validate the DBFile in the database
        List<DBFile> dBFileList = dBFileRepository.findAll();
        assertThat(dBFileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDBFile() throws Exception {
        // Initialize the database
        dBFileService.save(dBFile);

        int databaseSizeBeforeDelete = dBFileRepository.findAll().size();

        // Get the dBFile
        restDBFileMockMvc.perform(delete("/api/db-files/{id}", dBFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DBFile> dBFileList = dBFileRepository.findAll();
        assertThat(dBFileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DBFile.class);
        DBFile dBFile1 = new DBFile();
        dBFile1.setId(1L);
        DBFile dBFile2 = new DBFile();
        dBFile2.setId(dBFile1.getId());
        assertThat(dBFile1).isEqualTo(dBFile2);
        dBFile2.setId(2L);
        assertThat(dBFile1).isNotEqualTo(dBFile2);
        dBFile1.setId(null);
        assertThat(dBFile1).isNotEqualTo(dBFile2);
    }
}
