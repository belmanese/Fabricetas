package com.fabricetas.domain;

import com.fabricetas.domain.dto.StampDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * Entity that models the stamp of a user
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@NoArgsConstructor
@Table(name="STAMP")
@EqualsAndHashCode(exclude = {"stampId"})
public class Stamp implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue
    @Column(name = "STAMP_ID")
	private Integer stampId;

	@Getter @Setter
	@Column(name = "DESCRIPTION")
	private String description;

	@Getter @Setter
    @Column(name = "NAME")
	private String name;

	@Getter @Setter
    @Column(name = "PATH")
	private String path;

	@Getter @Setter
    @Column(name = "PRICE")
	private String price;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_COLOR_STAMP",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "COLOR_ID") })
	private Collection<Color> color;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_INVOICE_STAMP",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "INVOICE_ID") })
	private Collection<Invoice> invoice;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_STAMP_FILE",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "FILE_ID") })
	private Collection<PersistentFile> persistentFile;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_RATING_STAMP",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "RATING_ID") })
	private Collection<Rating> rating;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_STAMP_TEXT",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "TEXT_ID") })
	private Collection<Text> text;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="THEME_ID")
	private Theme theme;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_STAMP_TSHIRT",
			joinColumns = { @JoinColumn(name = "STAMP_ID") },
			inverseJoinColumns = { @JoinColumn(name = "TSHIRT_ID") })
	private Collection<Tshirt> tshirt;

	@ManyToOne
	@JsonIgnore
	@Getter @Setter
	@JoinColumn(name="USER_ID")
	private User user;

	@JsonIgnore
	public StampDto getDto(){
		return new StampDto(description, name, path, price, stampId);
	}

}
