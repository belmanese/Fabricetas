package com.fabricetas.domain;

import com.fabricetas.domain.dto.TshirtDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity that models the tshirt of a user/invoice
 * Created on 13/04/2017.
 * @author belman
 */
@Entity
@ToString
@Table(name="TSHIRT")
@EqualsAndHashCode(exclude = {"tshirtId"})
public class Tshirt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
    @Id @GeneratedValue	
    @Column(name = "TSHIRT_ID")
	private Integer tshirtId;

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
	@Getter @Setter
	@ManyToMany(mappedBy="tshirt")
	private Collection<Color> color;

	@JsonIgnore
	@ManyToMany
	@Getter @Setter
	@JoinTable(name = "R_INVOICE_TSHIRT",
			joinColumns = { @JoinColumn(name = "TSHIRT_ID") },
			inverseJoinColumns = { @JoinColumn(name = "INVOICE_ID") })
	private Collection<Invoice> invoice;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="tshirt")
	private Collection<Material> material;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="tshirt")
	private Collection<Stamp> stamp;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="tshirt")
	private Collection<Text> text;

	@JsonIgnore
	@Getter @Setter
	@ManyToMany(mappedBy="tshirt")
	private Collection<User> user;

	@JsonIgnore
	public TshirtDto getDto(){
		return new TshirtDto(description, name, path, price, tshirtId);
	}

}
