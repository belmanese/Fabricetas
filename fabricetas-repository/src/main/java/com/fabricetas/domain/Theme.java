package com.fabricetas.domain;

import com.fabricetas.domain.dto.ThemeDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity that models the theme of a stamp/image
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="THEME")
@EqualsAndHashCode(exclude = {"themeId"})
public class Theme implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "THEME_ID")
	private Integer themeId;

	@Getter @Setter
    @Column(name = "DESCRIPTION")  
	private String description;

	@Getter @Setter
    @Column(name = "NAME")  
	private String name;

	@Getter @Setter
    @Column(name = "PATH")  
	private String path;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="theme")
	private Collection<PersistentFile> persistentFile;

	@JsonIgnore
	@Getter @Setter
	@OneToMany(mappedBy="theme")
	private Collection<Stamp> stamp;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="theme")
	private Collection<User> user;

	@JsonIgnore
	public ThemeDto getDto(){
		return new ThemeDto(description, name, path, themeId);
	}

}
