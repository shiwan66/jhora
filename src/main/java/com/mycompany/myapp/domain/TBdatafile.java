package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TBdatafile.
 */
@Entity
@Table(name = "t_bdatafile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TBdatafile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tb_file_name")
    private String tbFileName;

    @Column(name = "tb_file_type")
    private String tbFileType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTbFileName() {
        return tbFileName;
    }

    public TBdatafile tbFileName(String tbFileName) {
        this.tbFileName = tbFileName;
        return this;
    }

    public void setTbFileName(String tbFileName) {
        this.tbFileName = tbFileName;
    }

    public String getTbFileType() {
        return tbFileType;
    }

    public TBdatafile tbFileType(String tbFileType) {
        this.tbFileType = tbFileType;
        return this;
    }

    public void setTbFileType(String tbFileType) {
        this.tbFileType = tbFileType;
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
        TBdatafile tBdatafile = (TBdatafile) o;
        if (tBdatafile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tBdatafile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TBdatafile{" +
            "id=" + getId() +
            ", tbFileName='" + getTbFileName() + "'" +
            ", tbFileType='" + getTbFileType() + "'" +
            "}";
    }
}
