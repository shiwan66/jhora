package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MaskDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Mask and its DTO MaskDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaskMapper extends EntityMapper<MaskDTO, Mask> {



    default Mask fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mask mask = new Mask();
        mask.setId(id);
        return mask;
    }
}
