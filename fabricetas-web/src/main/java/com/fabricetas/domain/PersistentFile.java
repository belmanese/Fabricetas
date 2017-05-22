package com.fabricetas.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import com.fabricetas.domain.dto.PersistentFileDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity that models the image of a user/stamp/tshirt
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="FILE")
@EqualsAndHashCode(exclude = {"fileId"})
public class PersistentFile implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Column(name="FILE_ID")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer fileId;

	@Getter @Setter
	@Column(name="CONTENT")
	@Lob @Basic(fetch = FetchType.LAZY)
	private byte[] content;

	@Getter @Setter
	@Column(name="DESCRIPTION")
	private String description;

	@Getter @Setter
	@Column(name="NAME")
	private String name;

	@Getter @Setter
	@Column(name="PATH")
	private String path;

	@Getter @Setter
	@Column(name="TYPE")
	private String type;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_COLOR_FILE",
			joinColumns = { @JoinColumn(name = "FILE_ID") },
			inverseJoinColumns = { @JoinColumn(name = "COLOR_ID") })
	private Collection<Color> color;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="persistentFile")
	private Collection<Stamp> stamp;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="THEME_ID")
	private Theme theme;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="USER_ID")
	private User user;

	@JsonIgnore
	public PersistentFileDto getDto(){
		return new PersistentFileDto(content, description, fileId, name, path, type);
	}

}
