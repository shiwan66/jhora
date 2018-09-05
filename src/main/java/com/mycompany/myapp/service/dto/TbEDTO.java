package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TbE entity.
 */
public class TbEDTO implements Serializable {

    private Long id;

    private String colFileName;

    private String colFileType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColFileName() {
        return colFileName;
    }

    public void setColFileName(String colFileName) {
        this.colFileName = colFileName;
    }

    public String getColFileType() {
        return colFileType;
    }

    public void setColFileType(String colFileType) {
        this.colFileType = colFileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TbEDTO tbEDTO = (TbEDTO) o;
        if(tbEDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tbEDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TbEDTO{" +
            "id=" + getId() +
            ", colFileName='" + getColFileName() + "'" +
            ", colFileType='" + getColFileType() + "'" +
            "}";
    }
}
