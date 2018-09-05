package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TbE.
 */
@Entity
@Table(name = "tb_e")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TbE implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "col_file_name")
    private String colFileName;

    @Column(name = "col_file_type")
    private String colFileType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColFileName() {
        return colFileName;
    }

    public TbE colFileName(String colFileName) {
        this.colFileName = colFileName;
        return this;
    }

    public void setColFileName(String colFileName) {
        this.colFileName = colFileName;
    }

    public String getColFileType() {
        return colFileType;
    }

    public TbE colFileType(String colFileType) {
        this.colFileType = colFileType;
        return this;
    }

    public void setColFileType(String colFileType) {
        this.colFileType = colFileType;
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
        TbE tbE = (TbE) o;
        if (tbE.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tbE.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TbE{" +
            "id=" + getId() +
            ", colFileName='" + getColFileName() + "'" +
            ", colFileType='" + getColFileType() + "'" +
            "}";
    }
}
