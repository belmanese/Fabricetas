package com.fabricetas.domain.dto;

import com.fabricetas.domain.Tshirt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

/**
 * Entity that models a material for view layer
 * Created on 22/04/2017.
 * @author belman
 */
@ToString
@EqualsAndHashCode(exclude={"materialId"})
public class MaterialDto {

    @Getter @Setter
    private Integer materialId;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private Collection<Tshirt> tshirt;

    public MaterialDto(String name, Integer materialId) {
        this.name = name; this.materialId = materialId;
    }
}
