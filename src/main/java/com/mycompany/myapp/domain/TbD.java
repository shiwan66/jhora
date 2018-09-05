package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TbD.
 */
@Entity
@Table(name = "tb_d")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TbD implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public TbD fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public TbD fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TbD tbD = (TbD) o;
        if (tbD.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tbD.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TbD{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", fileType='" + getFileType() + "'" +
            "}";
    }
}
