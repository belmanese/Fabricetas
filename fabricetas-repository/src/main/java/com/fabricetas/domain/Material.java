package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fabricetas.domain.dto.MaterialDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the Material of a tshirt
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="MATERIAL")
@EqualsAndHashCode(exclude = {"materialId"})
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "MATERIAL_ID")
	private Integer materialId;

	@Getter @Setter
    @Column(name = "NAME")  
	private String name;

    @JsonIgnore
    @ManyToMany
    @Getter @Setter
    @JoinTable(name = "R_MATERIAL_TSHIRT",
            joinColumns = { @JoinColumn(name = "MATERIAL_ID") },
            inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
    private Collection<Tshirt> tshirt;

    @JsonIgnore
    public MaterialDto getDto(){
        return new MaterialDto(name, materialId);
    }

}
