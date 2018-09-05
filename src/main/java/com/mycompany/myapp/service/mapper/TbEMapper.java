package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TbEDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TbE and its DTO TbEDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TbEMapper extends EntityMapper<TbEDTO, TbE> {



    default TbE fromId(Long id) {
        if (id == null) {
            return null;
        }
        TbE tbE = new TbE();
        tbE.setId(id);
        return tbE;
    }
}
