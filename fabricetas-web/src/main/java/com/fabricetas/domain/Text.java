package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fabricetas.domain.dto.TextDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the text of a user/stamp/tshirt
 * Created on 12/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="TEXT")
@EqualsAndHashCode(exclude = {"textId"})
public class Text implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "TEXT_ID")
	private Integer textId;

	@Getter @Setter
    @Column(name = "NAME")  
	private String name;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="text")
    private Collection<Color> color;

    @JsonIgnore
    @Getter @Setter
    @ManyToMany(mappedBy="text")
    private Collection<Stamp> stamp;

    @JsonIgnore
    @ManyToMany
    @Getter @Setter
    @JoinTable(name = "R_TSHIRT_TEXT",
            joinColumns = { @JoinColumn(name = "TEXT_ID") },
            inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
    private Collection<Tshirt> tshirt;

    @ManyToOne
    @JsonIgnore
    @Getter @Setter
    @JoinColumn(name="USER_ID")
    private User user;

    @JsonIgnore
    public TextDto getDto(){
        return new TextDto(name, textId);
    }
	
}
