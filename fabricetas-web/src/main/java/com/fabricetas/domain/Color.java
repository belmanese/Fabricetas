package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fabricetas.domain.dto.ColorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the color of a file/stamp/text/tshirt
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="COLOR")
@EqualsAndHashCode(exclude = {"colorId"})
public class Color implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "COLOR_ID")
	private Integer colorId;

	@Getter @Setter
    @Column(name = "NAME")  
	private String name;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="color")
    private Collection<PersistentFile> persistentFile;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="color")
    private Collection<Stamp> stamp;

    @JsonIgnore
    @ManyToMany
    @Getter @Setter
    @JoinTable(name = "R_COLOR_TSHIRT",
            joinColumns = { @JoinColumn(name = "COLOR_ID") },
            inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
    private Collection<Text> text;

    @JsonIgnore
    @ManyToMany
    @Getter @Setter
    @JoinTable(name = "R_COLOR_TSHIRT",
            joinColumns = { @JoinColumn(name = "COLOR_ID") },
            inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
    private Collection<Tshirt> tshirt;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="color")
    private Collection<User> user;

    @JsonIgnore
    public ColorDto getDto(){
        return new ColorDto(colorId, name);
    }

}
